package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.databricks.upload.UploadCsvToDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.upload.WriteFileStoreCsvToTable;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A03_UploadTestDatasetCsvFilesDatabricks {

	private static final File WORKING_DIR = new File("C:\\temp\\demo_cdm");

	private static final InputStream ZIP_SRC = FileUtil.getInputStream("/databricks/demo_cdm.zip");
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Uploading csv files...");
			String schemaName = DatabricksProperties.getSchemaName();
			String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
			exec(schemaName, databricksFilesRoot, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, String databricksFilesRoot, Connection conn) {
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		String uploadRoot = DatabricksProperties.getDatabricksUploadRoot();
		String databaseName = DatabricksProperties.getSchemaName();
		databricksFilesRoot = uploadRoot + "/" + databricksFilesRoot + "/csv";
		// delete existing files
		log.info("Deleting existing files...");
		log.info("Databricks file dir: \n" + databricksFilesRoot);
		DeleteCsvFromDatabricks.exec(databricksFilesRoot, true);
		// write files
		log.info("Writing zip file to working dir...");
		// clean up dirs
		FileUtil.rmdir(WORKING_DIR);
		FileUtil.mkdirs(WORKING_DIR);
		// copy and extract the zip file
		File zipFile = new File(WORKING_DIR, "demo_cdm.zip");
		FileUtil.write(ZIP_SRC, zipFile);
		ZipUtil.unzip(zipFile, WORKING_DIR);
		// get the list of files
		File srcDir = new File(WORKING_DIR, "demo_cdm");
		List<File> dirs = FileUtil.list(srcDir);
		log.info("Got " + dirs.size() + " dirs");
		for (File dir : dirs) {
			String tableName = dir.getName();
			log.info("TABLE: " + tableName);
			if("concept_recommended".equalsIgnoreCase(tableName)) {
				// skip this file (i don't know where this table came from)
				continue;
			}
			truncateTable(databaseName, tableName, conn);
			processDir(dir, databricksFilesRoot, databaseName, conn);
		}
		log.info("Got " + dirs.size() + " dirs");
		log.info("Done uploading test data.");
	}

	private static void processDir(File dir, String databricksFilesRoot, String databaseName, Connection conn) {
		log.info("Start processing dir: " + FileUtil.getCanonicalPath(dir));
		for (File file : dir.listFiles()) {
			uploadFile(file, databricksFilesRoot, databaseName, conn);
		}
		log.info("Done processing dir: " + FileUtil.getCanonicalPath(dir));
	}

	private static String uploadFile(File file, String databricksFilesRoot, String databaseName, Connection conn) {
		String fileName = file.getName();
		String parentName = file.getParentFile().getName();
		String databricksFilePath = databricksFilesRoot + "/" + parentName + "/" + fileName;
		log.info("Doing upload for file...");
		log.info(FileUtil.getCanonicalPath(file));
		log.info("Path: " + databricksFilePath);
		log.info("Uploading csv file...");
		UploadCsvToDatabricks.exec(databricksFilePath, file);
		log.info("Creating data table...");
		log.info("Done with upload");
		WriteFileStoreCsvToTable.exec(databricksFilePath, databaseName, parentName, conn);
		return databricksFilePath;
	}

	private static void truncateTable(String databaseName, String tableName, Connection conn) {
		String sqlString = "truncate table " + databaseName + "." + tableName;
		Database.update(sqlString, conn);
	}
	
}
