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
	}

	public static void exec() {
		// we use our own connection here to avoid "DROP DATABASE cannot run inside a transaction block" exception
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			log.info("Creating databases...");
			String databaseName = AppParams.getDbName();
			createDatabase(databaseName, conn);
			createDatabase(databaseName + "_dqd_results", conn);
			log.info("Done creating databases.");
		} finally {
			Database.close(conn);
		}
	}
	
	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		Database.update("drop database if exists " + databaseName, conn);
		Database.update("create database " + databaseName, conn);
	}
	
}
