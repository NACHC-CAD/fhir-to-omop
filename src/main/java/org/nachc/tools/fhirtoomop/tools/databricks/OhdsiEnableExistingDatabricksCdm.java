package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.A04_CreateAchilliesDatabasesDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A05_CreateAchillesDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A06_UploadAchillesAnalysisDetailsCsv;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A07_RunAchillesScript;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhdsiEnableExistingDatabricksCdm {

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
		// timer
		Timer timer = new Timer();
		timer.start();
		// install and populate achilles
		A04_CreateAchilliesDatabasesDatabricks.exec(achillesTempSchemaName, achillesResultsSchemaName, conn);
		A05_CreateAchillesDatabaseObjectsDatabricks.exec(vocabSchemaName, achillesTempSchemaName, achillesResultsSchemaName, conn);
		A06_UploadAchillesAnalysisDetailsCsv.exec(databricksFilesRoot, achillesResultsSchemaName, conn);
		// run the achilles script
		A07_RunAchillesScript.exec(schemaName, achillesResultsSchemaName, conn);
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
