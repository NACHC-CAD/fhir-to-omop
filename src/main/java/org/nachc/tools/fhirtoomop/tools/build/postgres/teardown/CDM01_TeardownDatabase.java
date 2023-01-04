package org.nachc.tools.fhirtoomop.tools.build.postgres.teardown;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM01_TeardownDatabase {

	public static void main(String[] args) {
	}

	public static void exec() {
		// we use our own connection here to avoid "DROP DATABASE cannot run inside a transaction block" exception
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			log.info("Dropping CDM databases...");
			String databaseName = AppParams.getDbName();
			dropDatabase(databaseName, conn);
			dropDatabase(databaseName + "_dqd_results", conn);
			log.info("Done dropping CDM databases.");
		} finally {
			Database.close(conn);
		}
	}
	
	private static void dropDatabase(String databaseName, Connection conn) {
		log.info("Dropping database: " + databaseName);
		Database.update("drop database if exists " + databaseName, conn);
	}
	
}
