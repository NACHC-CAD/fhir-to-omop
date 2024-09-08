package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM01b_CreateCdmSchema {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		log.info("Creating schema...");
		String databaseName = AppParams.getFullySpecifiedCdmSchemaName();
		createSchema(databaseName, conn);
		log.info("Done creating databases.");
	}

	private static void createSchema(String databaseName, Connection conn) {
		log.info("Creating schema: " + databaseName);
		Database.update("drop schema if exists " + databaseName, conn);
		Database.update("create schema " + databaseName, conn);
	}

}
