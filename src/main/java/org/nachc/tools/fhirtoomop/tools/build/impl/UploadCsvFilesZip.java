package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCsvFilesZip {

	public static void exec(Connection conn, InputStream is, File outputDir) {
		try {
			log.info("Cleaning up files...");
			FileUtil.rmdir(outputDir);
			FileUtil.mkdirs(outputDir);
			log.info("Unzipping file...");
			ZipUtil.unzip(is, outputDir);
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(outputDir));
			File srcDir = outputDir;
			File[] files = srcDir.listFiles();
			log.info("Got " + files.length + " files...");
			for(File file : files) {
				if(file.isDirectory() == false) {
					uploadFile(conn, file);
				}
			}
			log.info("-----------");
			log.info("Done uploading files.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			FileUtil.rmdir(outputDir);
		}
	}

	private static void uploadFile(Connection conn, File file) {
		String fileName = file.getName();
		String tableName = fileName.substring(0, fileName.indexOf("."));
		log.info("-----------");
		log.info("Uploading file: " + FileUtil.getCanonicalPath(file));
		log.info("Table Name: " + tableName);
		if(skip(tableName) == true) {
			log.info("SKIPPING TABLE: " + tableName);
			return;
		}
		String sqlString = "";
		sqlString += "BULK INSERT " + tableName + " \n";
		sqlString += "FROM '" + FileUtil.getCanonicalPath(file) + "' \n";
		sqlString += "WITH \n";
		sqlString += "( \n";
		sqlString += "    FIELDTERMINATOR = ',', \n";
		sqlString += "    ROWTERMINATOR = '\n', \n";
		sqlString += "    FIRSTROW = 2, \n";
		sqlString += "    FORMAT = 'CSV', \n";
		sqlString += "    TABLOCK \n";
		sqlString += ") \n";		Database.update("truncate table " + tableName, conn);
		Database.update(sqlString, conn);
		log.info("Done with file: " + FileUtil.getCanonicalPath(file));
	}

	private static boolean skip(String tableName) {
		if("concept_recommended".equals(tableName)) {
			return true;
		} else if("attribute_definition".equals(tableName)) {
			return true;
		} else {
			return false;
		}
	}
	
}
