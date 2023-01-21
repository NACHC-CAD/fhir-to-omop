package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR06_CreateSyntheaNative {

	public static void main(String[] args) {
		log.info("Starting main...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			String schemaName = AppParams.get("syntheaNative");
			Database.update("drop schema if exists " + schemaName + " cascade", conn);
			Database.update("create schema " + schemaName, conn);
		} finally {
			Database.close(conn);
		}
	}
}
