package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR00a_CreateOhdsiDatabaseForWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR00b_CreateAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR00c_CreateAtlasWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR00d_CreateAtlasWebApiTables;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR04_CreateAchilliesDatabasesDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR05_CreateAchillesDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR06_UploadAchillesAnalysisDetailsCsv;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR07_RunAchillesScript;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR08_DeleteAchillesWebApiRecords;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR09_CreateAchillesWebApiRecords;
import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhdsiEnableExistingDatabricksCdm {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}

	public static void exec() {
		// delete and create the database to hold the webapi schema (required bootstrap connection)
		DBR00a_CreateOhdsiDatabaseForWebApiSchema.exec();
		// build the webapi schema
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksWebApiConnectionFactory.getConnection();
			log.info("Building Databricks instance...");
			String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
			String schemaName = DatabricksProperties.getSchemaName();
			String vocabSchemaName = DatabricksProperties.getVocabSchemaName();
			String achillesTempSchemaName = DatabricksProperties.getAchillesTempSchemaName();
			String achillesResultsSchemaName = DatabricksProperties.getAchillesResultsSchemaName();
			buildWebApi(databricksFilesRoot, schemaName, vocabSchemaName, achillesTempSchemaName, achillesResultsSchemaName, conn);
			log.info("Done building instance.");
		} finally {
			try {
				DatabricksDatabase.close(conn);
			} catch (Exception exp) {
				log.info("An exception was thrown trying to close the connection. This seems to happen with Databricks sometimes");
			}
		}
	}

	private static void buildWebApi(String databricksFilesRoot, String schemaName, String vocabSchemaName, String achillesTempSchemaName, String achillesResultsSchemaName, Connection conn) {
		// timer
		Timer timer = new Timer();
		timer.start();
		// create the webapi schema
		DBR00b_CreateAtlasDatabaseUsers.exec(conn);
		DBR00c_CreateAtlasWebApiSchema.exec(conn);
		DBR00d_CreateAtlasWebApiTables.exec(conn);
		// install and populate achilles
		DBR04_CreateAchilliesDatabasesDatabricks.exec(achillesTempSchemaName, achillesResultsSchemaName, conn);
		DBR05_CreateAchillesDatabaseObjectsDatabricks.exec(vocabSchemaName, achillesTempSchemaName, achillesResultsSchemaName, conn);
		DBR06_UploadAchillesAnalysisDetailsCsv.exec(databricksFilesRoot, achillesResultsSchemaName, conn);
		// run the achilles script
		DBR07_RunAchillesScript.exec(schemaName, achillesResultsSchemaName, conn);
		// delete and create the webapi records
		DBR08_DeleteAchillesWebApiRecords.exec(conn);
		DBR09_CreateAchillesWebApiRecords.exec(conn);
		// output timer info
		timer.stop();
		log.info("-----------------");
		log.info("Done creating the test database.");
		log.info("Start time:  " + timer.getStartAsString());
		log.info("End time:    " + timer.getStopAsString());
		log.info("Elapsed:     " + timer.getElapsedString());
		log.info("-----------------");
		log.info("Done running scripts.");
	}

}
