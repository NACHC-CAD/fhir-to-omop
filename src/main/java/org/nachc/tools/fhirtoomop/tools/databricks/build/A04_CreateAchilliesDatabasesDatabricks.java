package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * CDM 5.3
 * 
 * 
 *
 */
@Slf4j
public class A04_CreateAchilliesDatabasesDatabricks {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String achillesTempDatabaseName = DatabricksProperties.getAchillesTempDatabaseName();
			String achillesResultsDatabaseName = DatabricksProperties.getAchillesResultsDatabaseName();
			exec(achillesTempDatabaseName, achillesResultsDatabaseName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String tempDatabaseName, String resultsDatabaseName, Connection conn) {
		// check the connection 
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("START: Creating Achilles Databases (temp and results): " + tempDatabaseName + ", " + resultsDatabaseName);
		log.info("-------------------------------");
		try {
			log.info("--- CREATING TEMP SCHEMA -------------------------");
			createDatabase(tempDatabaseName, conn);
			log.info("--- CREATING RESULTS SCHEMA ----------------------");
			createDatabase(resultsDatabaseName, conn);
			log.info("--- DONE CREATING ACHILLES SCHEMAS ---------------");
			log.info("Achilles schemas have been created (tables have not yet been created)");
		} finally {
			Database.close(conn);
		}
		// echo status
		log.info("-------------------------------");
		log.info("DONE: Creating Achilles Databases (temp and results): " + tempDatabaseName + ", " + resultsDatabaseName);
		log.info("-------------------------------");
	}

	private static void createDatabase(String databaseName, Connection conn) {
		log.info("DROPPING AND CREATING ACHILLES DATABASE: " + databaseName);
		log.info("Dropping database: " + databaseName);
		Database.update("drop database if exists " + databaseName + " cascade", conn);
		log.info("Creating database: " + databaseName);
		Database.update("create database " + databaseName, conn);
		log.info("Done creating Achilles database (no tables created yet): " + databaseName);
	}
}
