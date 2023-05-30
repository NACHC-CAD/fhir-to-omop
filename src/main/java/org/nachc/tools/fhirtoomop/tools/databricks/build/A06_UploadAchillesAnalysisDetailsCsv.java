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

/**
 * 
 * CDM ???
 * I would think this would be versioned, but it looks like the file from the latest Achilles build works.  
 * (I'm assuming it will work for both 5.3 and 5.4).  
 * 
 * This class creates and populates the achilles_analysis table.
 * For some reason this table is not included in the script created when we run Achilles in sqlOnly mode.  
 * Therefore, it needs to be created here from the csv file.  
 * See: https://forums.ohdsi.org/t/error-running-achilles-against-databricks/18575 
 * Note: The csv file is achilles_analysis_details.csv, the table is achilles_analysis (no _details in the table name).  
 *
 */

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
			String achillesResultsSchemaName = DatabricksProperties.getAchillesResultsSchemaName();
			exec(databricksFilesRoot, achillesResultsSchemaName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	public static void exec(String databricksFilesRoot, String achillesResultsSchemaName, Connection conn) {
		// check the connection 
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("START: Creating achilles_analysis table from achilles_analysis_details.csv");
		log.info("-------------------------------");
		// uploading the csv file
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
		createTable(achillesResultsSchemaName, conn);
		// populate the table from the file
		WriteFileStoreCsvToTable.exec(path, achillesResultsSchemaName, TABLE_NAME, conn);
		// echo status
		log.info("-------------------------------");
		log.info("START: Creating achilles_analysis table from achilles_analysis_details.csv");
		log.info("-------------------------------");
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
