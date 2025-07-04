package org.nachc.tools.fhirtoomop.tools.build.impl.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.uploadcsv.FileUploader;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class UploadCsvFilesFromZipForPostgres extends FileUploader {

	private static final String ZIP_DIR = AppParams.getCdmCsvZipFileLocation();

	private static final String ZIP_NAME = AppParams.getCdmCsvZipFileName();

	private static final String URL = AppParams.getCdmCsvDownloadUrl();

	private static final boolean DOWNLOAD = AppParams.getCdmCsvDownloadIfNotFound();

	private static final String DB = AppParams.getFullySpecifiedDatabaseName();

	private static final String SCHEMA = AppParams.getFullySpecifiedCdmSchemaName();

	public static void uploadAll(Connection conn) {
		File file = null;
		try {
			download();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			new UploadCsvFilesFromZipForPostgres().exec(DB, SCHEMA, is, unzipDir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info("Done with upload.");
	}

	public static void uploadDatatables(Connection conn) {
		File file = null;
		try {
			download();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			List<String> ignoreList = VocabularyTablesList.getTablesList();
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			UploadCsvFilesFromZipForPostgres upload = new UploadCsvFilesFromZipForPostgres();
			upload.setIgnoreList(ignoreList);
			upload.exec(DB, SCHEMA, is, dir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info("Done with upload.");
	}

	public static void uploadTerminologyTables(Connection conn) {
		File file = null;
		try {
			download();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			List<String> ignoreList = VocabularyTablesList.getTablesList();
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			UploadCsvFilesFromZipForPostgres upload = new UploadCsvFilesFromZipForPostgres();
			upload.setIgnoreList(ignoreList);
			upload.setInvertIgnore(true);
			upload.exec(DB, SCHEMA, is, dir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info("Done with upload.");
	}

	private static void download() {
		log.info("Looking for existing file");
		File dir = new File(ZIP_DIR);
		File out = new File(dir, ZIP_NAME);
		if (out.exists() == false && DOWNLOAD == true) {
			FileUtil.mkdirs(dir);
			log.info("Doing download");
			log.info("DOWNLOAD: " + DOWNLOAD);
			log.info("DIR: " + FileUtil.getCanonicalPath(dir));
			HttpRequestClient client = new HttpRequestClient(URL);
			client.doGet();
			FileUtil.write(client.getResponseInputStream(), out);
		} else {
			log.info("Skipping download");
			log.info("DOWNLOAD: " + DOWNLOAD);
			log.info("DIR: " + FileUtil.getCanonicalPath(dir));
		}
	}

	protected void uploadFile(String dbName, String schemaName, File file, Connection conn) {
		String fileName = file.getName();
		String tableName = fileName.substring(0, fileName.indexOf("."));
		String fullName = schemaName + "." + tableName;
		log.info("-----------");
		log.info("Uploading file: " + FileUtil.getCanonicalPath(file));
		log.info("Table Name: " + fullName);
		if(ignore(tableName) == true) {
			log.info("SKIPPING TABLE: " + fullName);
			this.ignored.add(fullName);
			return;
		}
		try {
			String dockerFileName = FileUtil.getCanonicalPath(file);
			dockerFileName = dockerFileName.replace("C:", "");
			dockerFileName = dockerFileName.replaceAll("\\\\", "/");
			String sqlString = "";
			sqlString += "COPY " + fullName + " \n";
			sqlString += "FROM '" + dockerFileName + "' \n";
			sqlString += "WITH \n";
			sqlString += "( \n";
			sqlString += "    FORMAT csv, \n";
			sqlString += "    DELIMITER ',', \n";
			sqlString += "    HEADER \n";
			sqlString += ") \n";		
			Database.update(sqlString, conn);
			this.success.add(fullName);
		} catch(Exception exp) {
			log.info("COULD NOT UPLOAD FILE (EXCEPTION OCCURRED): ");
			log.info(exp.getMessage());
			this.failure.add(fullName);
		}
		log.info("Done with file: " + FileUtil.getCanonicalPath(file));
	}

}
