package org.nachc.tools.fhirtoomop.tools.syntheacsv;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.mssql.MsSqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadSyntheaCsvFiles {

	public static void main(String[] args) {
		log.info("Uploading Synthea CSV files...");
		Connection conn = MsSqlDatabaseConnectionFactory.getBootstrapConnection();
		exec(conn);
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String databaseName = AppParams.getSyntheaCsvNativeSchema();
		dropDatabase(databaseName, conn);
		createDatabase(databaseName, conn);
	}

	private static boolean databaseExists(String databaseName, Connection conn) {
		String sqlString = "SELECT * FROM sys.databases WHERE name = '" + databaseName + "'";
		Data data = Database.query(sqlString, conn);
		if(data.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void dropDatabase(String databaseName, Connection conn) {
		boolean databaseExists = databaseExists(databaseName, conn);
		if(databaseExists) {
			log.info("Dropping database: " + databaseName);
			String sqlString = "drop database " + databaseName;
			log.info("Droping database: \n" + sqlString);
			Database.update(sqlString, conn);
			log.info("Done dropping database.");
		} else {
			log.info("Database doesn't exist, skipping drop.");
		}
		log.info("Done with drop.");
	}
	
	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		String sqlString = "create database " + databaseName;
		Database.update(sqlString, conn);
		log.info("Done creating database.");
	}
	
}
