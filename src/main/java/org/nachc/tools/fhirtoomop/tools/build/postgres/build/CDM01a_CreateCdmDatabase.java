package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.DropDatabaseForPostgres;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM01a_CreateCdmDatabase {

	public static void exec(Connection conn) {
		log.info("Creating database...");
		String databaseName = AppParams.getFullySpecifiedDatabaseName();
		createDatabase(databaseName, conn);
		log.info("Done creating databases.");
	}

	private static void createDatabase(String databaseName, Connection conn) {
		if("postgres".equalsIgnoreCase(databaseName)) {
			log.info("! ! ! SKIPPING CREATE DATABASE: Not allowed to overwrite the postgres database");
		} else {
			try {
				log.info("Creating database: " + databaseName);
				conn.setAutoCommit(true);
				DropDatabaseForPostgres.exec(conn);
				conn.setAutoCommit(true);
				Database.update("create database " + databaseName, conn);
			} catch(Exception exp) {
				throw new RuntimeException(exp);
			}
		}
	}

}
