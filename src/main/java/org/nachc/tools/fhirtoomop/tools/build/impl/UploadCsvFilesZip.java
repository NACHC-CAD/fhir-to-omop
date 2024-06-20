package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCsvFilesZip {

	private ArrayList<String> ignoreList = new ArrayList<String>();
	
	private ArrayList<String> success = new ArrayList<String>();
	
	private ArrayList<String> failure = new ArrayList<String>();
	
	private ArrayList<String> ignored = new ArrayList<String>();
	
	private boolean invertIgnore = false;

	public void exec(String dbName, String schemaName, InputStream is, File outputDir, Connection conn) {
		try {
			log.info("Unzipping file...");
			ZipUtil.unzip(is, outputDir);
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(outputDir));
			// get the list of files
			File srcDir = outputDir;
			File[] files = srcDir.listFiles();
			log.info("Got " + files.length + " files...");
			for(File file : files) {
				if(file.isDirectory() == false && isZip(file) == false) {
					uploadFile(dbName, schemaName, file, conn);
				}
			}
			logOutcomes();
			log.info("Done uploading files.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private void uploadFile(String dbName, String schemaName, File file, Connection conn) {
		String fileName = file.getName();
		String tableName = fileName.substring(0, fileName.indexOf("."));
		String fullName = dbName + "." + schemaName + "." + tableName;
		log.info("-----------");
		log.info("Uploading file: " + FileUtil.getCanonicalPath(file));
		log.info("Table Name: " + fullName);
		if(ignore(tableName) == true) {
			log.info("SKIPPING TABLE: " + fullName);
			this.ignored.add(fullName);
			return;
		}
		try {
			String sqlString = "";
			sqlString += "BULK INSERT " + fullName + " \n";
			sqlString += "FROM '" + FileUtil.getCanonicalPath(file) + "' \n";
			sqlString += "WITH \n";
			sqlString += "( \n";
			sqlString += "    FIELDTERMINATOR = ',', \n";
			sqlString += "    ROWTERMINATOR = '\n', \n";
			sqlString += "    FIRSTROW = 2, \n";
			sqlString += "    FORMAT = 'CSV', \n";
			sqlString += "    TABLOCK \n";
			sqlString += ") \n";		
			Database.update("use " + dbName, conn);
			Database.update(sqlString, conn);
			this.success.add(fullName);
		} catch(Exception exp) {
			log.info("COULD NOT UPLOAD FILE (EXCEPTION OCCURRED): ");
			log.info(exp.getMessage());
			this.failure.add(fullName);
		}
		log.info("Done with file: " + FileUtil.getCanonicalPath(file));
	}

	private boolean ignore(String tableName) {
		boolean rtn = false;
		if(this.ignoreList.contains(tableName.toUpperCase())) {
			rtn = true;
		} else {
			rtn = false;
		}
		if(invertIgnore == true) {
			rtn = !rtn;
		}
		return rtn;
	}
	
	private boolean isZip(File file) {
		String name = file.getName().toLowerCase();
		if(name.endsWith(".zip") == true) {
			return true;
		} else {
			return false;
		}
	}
	
	//
	// log the outcome
	//
	
	public void logOutcomes() {
		String msg;
		msg = "\n--------------------\n";
		msg += "Success: The following tables were successfully uploaded. \n";
		for (String str : success) {
			msg += "  " + str + "\n";
		}
		msg += "--------------------\n";
		log.info(msg);
		if (ignored.size() > 0) {
			msg = "\n--------------------\n";
			msg += "The following files were ignored: \n";
			for (String str : ignored) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "No files were ignored.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
		if (failure.size() > 0) {
			msg = "\n--------------------\n";
			msg += "! ! ! SOME TABLES FAILED ! ! !\n";
			for (String str : failure) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "There were no errors.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
	}

}
