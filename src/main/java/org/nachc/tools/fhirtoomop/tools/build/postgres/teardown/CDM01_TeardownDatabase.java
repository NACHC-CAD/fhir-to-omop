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
		Connection conn = null;
		// TODO: FIX THIS (need to figure out how to use boot strap connection and specify drop of /DATABASE/schema)
		try {
			try {
				conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
			} catch(Exception exp) {
				log.info("Could not open connection, probably already deleted database: " + exp.getMessage());
				return;
			}
			log.info("Dropping CDM databases...");
			String databaseName = AppParams.getSchemaName();
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
