package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.A01_CreateCdmDatabaseDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A02_CreateCdmDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A03_UploadTestDatasetCsvFilesDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildDatabricksTestInstance {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Building Databricks instance...");
			String schemaName = DatabricksProperties.getSchemaName();
			String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
			exec(schemaName, databricksFilesRoot, conn);
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

	public static void exec(String schemaName, String databricksFilesRoot, Connection conn) {
		log.info("CREATING DATABASE INSTANCE: " + schemaName);
		// create the cdm
		A01_CreateCdmDatabaseDatabricks.exec(schemaName, conn);
		A02_CreateCdmDatabaseObjectsDatabricks.exec(schemaName, conn);
		// upload test data
		A03_UploadTestDatasetCsvFilesDatabricks.exec(schemaName, databricksFilesRoot, conn);
		// TODO: next step is to run the python script in the databricks notebook
		// ACHILLES STUFF
		//		A04_CreateAchilliesDatabasesDatabricks.exec(databricksFilesRoot, conn);
		//		A05_CreateAchillesDatabaseObjectsDatabricks.exec(schemaName, conn);
		//		A06_UploadAchillesAnalysisDetailsCsv.exec(databricksFilesRoot);
		// TODO: run the achilles script
	}

}
