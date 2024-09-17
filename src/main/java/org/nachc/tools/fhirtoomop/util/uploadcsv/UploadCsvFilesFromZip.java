package org.nachc.tools.fhirtoomop.util.uploadcsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.build.impl.fileupload.FileUploaderForSqlServer;
import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class UploadCsvFilesFromZip {

	private static final String ZIP_DIR = AppParams.getCdmCsvZipFileLocation();

	private static final String ZIP_NAME = AppParams.getCdmCsvZipFileName();

	private static final String URL = AppParams.getCdmCsvDownloadUrl();

	private static final boolean DOWNLOAD = AppParams.getCdmCsvDownloadIfNotFound();

	private static final String DB = AppParams.getFullySpecifiedDatabaseName();

	private static final String SCHEMA = AppParams.getFullySpecifiedCdmSchemaName();

	public static void main(String[] args) {
		//		uploadAll();
		//		uploadDatatables();
		uploadTerminologyTables();
	}

	public static void uploadAll() {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		File file = null;
		try {
			download();
			FileUploaderForSqlServer upload = new FileUploaderForSqlServer();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			upload.exec(DB, SCHEMA, is, unzipDir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
		log.info("Done with upload.");
	}

	public static void uploadDatatables() {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		File file = null;
		try {
			download();
			FileUploaderForSqlServer upload = new FileUploaderForSqlServer();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			List<String> ignoreList = VocabularyTablesList.getTablesList();
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			upload.setIgnoreList(ignoreList);
			upload.exec(DB, SCHEMA, is, dir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
		log.info("Done with upload.");
	}

	public static void uploadTerminologyTables() {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		File file = null;
		try {
			download();
			FileUploaderForSqlServer upload = new FileUploaderForSqlServer();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			File unzipDir = new File(dir, "csv");
			InputStream is = new FileInputStream(file);
			List<String> ignoreList = VocabularyTablesList.getTablesList();
			FileUtil.rmdir(unzipDir);
			FileUtil.mkdirs(unzipDir);
			upload.setIgnoreList(ignoreList);
			upload.setInvertIgnore(true);
			upload.exec(DB, SCHEMA, is, dir, conn);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
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

}
