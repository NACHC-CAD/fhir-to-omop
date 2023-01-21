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
		String databaseName = AppParams.getDbName();
		dropDatabase(databaseName + "_ach_res", conn);
		dropDatabase(databaseName + "_ach_tmp", conn);
		Database.commit(conn);
		log.info("Done tearing down Achilles databases.");
	}

	private static void dropDatabase(String databaseName, Connection conn) {
		log.info("Dropping database: " + databaseName);
		Database.update("drop database if exists " + databaseName, conn);
	}

}
