package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.databricks.upload.WriteFileStoreCsvToTable;
import org.yaorma.database.Database;

import com.nach.core.util.databricks.file.DatabricksFileUtil;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A06_UploadAchillesAnalysisDetailsCsv {

	private static final String FILE_NAME = "achilles_analysis_details.csv";
	
	private static final String TABLE_NAME = "achilles_analysis";
	
	private static final String FILE_PATH = "/databricks/achilles/" + FILE_NAME;

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
			String databaseName = DatabricksProperties.getAchillesResultsDatabaseName();
			exec(databricksFilesRoot, databaseName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	public static void exec(String databricksFilesRoot, String databaseName, Connection conn) {
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		log.info("Uplaoding achilles_analysis_details.csv");
		String uploadRoot = DatabricksProperties.getDatabricksUploadRoot();
		databricksFilesRoot = uploadRoot + "/" + databricksFilesRoot + "/etc/achilles";
		log.info("Dir for achilles file: \n" + databricksFilesRoot);
		// delete existing files
		log.info("Deleting existing files...");
		DeleteCsvFromDatabricks.exec(databricksFilesRoot, true);
		// upload the file
		log.info("Databricks file dir: " + databricksFilesRoot);
		InputStream is = FileUtil.getInputStream(FILE_PATH);
		log.info("Got input stream: " + is);
		String path = uploadFile(is, databricksFilesRoot, FILE_NAME);
		// create the table
		log.info("Creating table from FileStore csv file...");
		createTable(databaseName, conn);
		// populate the table from the file
		WriteFileStoreCsvToTable.exec(path, databaseName, TABLE_NAME, conn);
	}
	
	private static String uploadFile(InputStream is, String databricksFilesRoot, String fileName) {
		log.info("Doing upload...");
		String url = DatabricksProperties.getRestUrl();
		String token = DatabricksProperties.getToken();
		String databricksFilePath = databricksFilesRoot + "/" + fileName;
		log.info("Uploading file to: " + databricksFilePath);
		new DatabricksFileUtil(url, token).put(databricksFilesRoot, is, fileName, true);
		log.info("Done with upload");
		return databricksFilePath;
	}

	private static void createTable(String databaseName, Connection conn) {
		String tableName = databaseName + "." + TABLE_NAME;
		String dropString = "drop table if exists " + tableName;
		log.info("Doing drop: " + tableName + "\n" + dropString);
		Database.update(dropString, conn);
		String sqlString = "";
		sqlString += "create table "+ tableName +" ( \n";
		sqlString += "  ANALYSIS_ID int, \n";
		sqlString += "  DISTRIBUTION int, \n";
		sqlString += "  DISTRIBUTED_FIELD string, \n";
		sqlString += "  ANALYSIS_NAME string, \n";
		sqlString += "  STRATUM_1_NAME string, \n";
		sqlString += "  STRATUM_2_NAME string, \n";
		sqlString += "  STRATUM_3_NAME string, \n";
		sqlString += "  STRATUM_4_NAME string, \n";
		sqlString += "  STRATUM_5_NAME string, \n";
		sqlString += "  IS_DEFAULT boolean, \n";
		sqlString += "  CATEGORY string \n";
		sqlString += ") \n";		
		log.info("Doing create: " + tableName + "\n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done creating table.");
	}
	
}
