package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR01_CreateCdmDatabaseDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR02_CreateCdmDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR03_UploadTestDatasetCsvFilesDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR04_CreateAchilliesDatabasesDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR05_CreateAchillesDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR06_UploadAchillesAnalysisDetailsCsv;
import org.nachc.tools.fhirtoomop.tools.databricks.build.DBR07_RunAchillesScript;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildDemoCdmInDatabricks {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Building Databricks instance...");
			String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
			String schemaName = DatabricksProperties.getSchemaName();
			String vocabSchemaName = DatabricksProperties.getVocabSchemaName();
			String achillesTempSchemaName = DatabricksProperties.getAchillesTempSchemaName();
			String achillesResultsSchemaName = DatabricksProperties.getAchillesResultsSchemaName();
			exec(databricksFilesRoot, schemaName, vocabSchemaName, achillesTempSchemaName, achillesResultsSchemaName, conn);
			log.info("Done building instance.");
		} finally {
			try {
				DatabricksDatabase.close(conn);
			} catch (Exception exp) {
				log.info("An exception was thrown trying to close the connection. This seems to happen with Databricks sometimes");
			}
		}
		log.info("Done.");
	}

	public static void exec(String databricksFilesRoot, String schemaName, String vocabSchemaName, String achillesTempSchemaName, String achillesResultsSchemaName, Connection conn) {
		log.info("CREATING DATABASE INSTANCE: " + schemaName);
		Timer timer = new Timer();
		timer.start();
		// create the cdm
		DBR01_CreateCdmDatabaseDatabricks.exec(schemaName, conn);
		DBR02_CreateCdmDatabaseObjectsDatabricks.exec(schemaName, conn);
		// upload test data
		DBR03_UploadTestDatasetCsvFilesDatabricks.exec(schemaName, databricksFilesRoot, conn);
		// output timer info
		timer.stop();
		log.info("-----------------");
		log.info("Done creating the test database.");
		log.info("Start time:  " + timer.getStartAsString());
		log.info("End time:    " + timer.getStopAsString());
		log.info("Elapsed:     " + timer.getElapsedString());
		log.info("-----------------");
		// done
		log.info("Done running scripts.");
	}

}
