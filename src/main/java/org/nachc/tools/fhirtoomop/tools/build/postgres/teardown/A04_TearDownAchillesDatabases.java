package org.nachc.tools.fhirtoomop.tools.build.postgres.teardown;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A04_TearDownAchillesDatabases {

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		String resName = AppParams.getFullySpecifiedAchillesResultsSchemaName();
		String tempName = AppParams.getFullySpecifiedAchillesTempSchemaName();
		String synthDbName = AppParams.getSyntheaCsvNativeDatabase();
		dropDatabase(resName, conn);
		dropDatabase(tempName, conn);
		dropDatabase(synthDbName, conn);
		Database.commit(conn);
		log.info("Done tearing down Achilles databases.");
	}

	private static void dropDatabase(String databaseName, Connection conn) {
		log.info("Dropping schema: " + databaseName);
		Database.update("drop schema if exists " + databaseName + " cascade", conn);
	}

}
