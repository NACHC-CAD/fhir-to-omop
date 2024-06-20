package org.nachc.tools.fhirtoomop.util.db.uploadcsv.sqlserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.impl.UploadCsvFilesZip;
import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCsvForSqlServer {

	private static final String ZIP_DIR = AppParams.getCdmCsvZipFileLocation();
	
	private static final String ZIP_NAME = AppParams.getCdmCsvZipFileName();
	
	private static final String URL = AppParams.getCdmCsvDownloadUrl();
	
	private static final boolean DOWNLOAD = AppParams.getCdmCsvDownloadIfNotFound();

	private static final String DB = AppParams.getDatabaseName();
	
	private static final String SCHEMA = AppParams.getSchemaName();
	
	public static void main(String[] args) {
		uploadAll();
	}
	
	public static void uploadAll() {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		File file = null;
		try {
			download();
			UploadCsvFilesZip upload = new UploadCsvFilesZip();
			File dir = new File(ZIP_DIR);
			file = new File(dir, ZIP_NAME);
			InputStream is = new FileInputStream(file);
			upload.exec(DB, SCHEMA, is, dir, conn);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
			file.delete();
		}
	}
	
	public static void uploadDatatables() {
		// TODO
	}
	
	public static void uploadTerminologyTables() {
		// TODO
	}
	
	private static void download() {
		log.info("Looking for existing file");
		File dir = new File(ZIP_DIR);
		File out = new File(dir, ZIP_NAME);
		if(dir.exists() == false && DOWNLOAD == true) {
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
