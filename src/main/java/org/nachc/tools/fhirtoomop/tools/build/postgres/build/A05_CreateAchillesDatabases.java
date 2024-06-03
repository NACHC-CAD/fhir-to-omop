package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A05_CreateAchillesDatabases {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Done creating Achilles databases.");
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			String achillesResDbName = AppParams.getAchillesResultsSchemaName();
			String achillesTempDbName = AppParams.getAchillesTempSchemaName();
			createDatabase(achillesResDbName, conn);
			createDatabase(achillesTempDbName, conn);
			Database.commit(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating Achilles databases.");
	}

	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		Database.update("drop schema if exists " + databaseName, conn);
		Database.update("create schema " + databaseName, conn);
	}

}
