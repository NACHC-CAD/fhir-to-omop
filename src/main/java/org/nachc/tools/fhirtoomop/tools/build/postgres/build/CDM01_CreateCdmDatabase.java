package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM01_CreateCdmDatabase {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			log.info("Creating databases...");
			String databaseName = AppParams.getSchemaName();
			createDatabase(databaseName, conn);
			createDatabase(databaseName + "_dqd_results", conn);
			createDatabase("synthea_native", conn);
			log.info("Done creating databases.");
		} finally {
			Database.close(conn);
		}
	}
	
	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		Database.update("drop schema if exists " + databaseName, conn);
		Database.update("create schema " + databaseName, conn);
	}
	
}
