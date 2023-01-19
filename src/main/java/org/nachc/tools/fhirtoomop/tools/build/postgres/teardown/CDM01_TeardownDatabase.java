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
		log.info("Starting main...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		// we use our own connection here to avoid "DROP DATABASE cannot run inside a transaction block" exception
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			log.info("Dropping CDM databases...");
			String databaseName = AppParams.getDbName();
			dropSchema(databaseName, conn);
			dropSchema(databaseName + "_dqd_results", conn);
			log.info("Done dropping CDM databases.");
		} finally {
			Database.close(conn);
		}
	}
	
	private static void dropSchema(String schemaName, Connection conn) {
		log.info("Dropping schema: " + schemaName);
		Database.update("drop schema if exists " + schemaName + " cascade", conn);
	}
	
}
