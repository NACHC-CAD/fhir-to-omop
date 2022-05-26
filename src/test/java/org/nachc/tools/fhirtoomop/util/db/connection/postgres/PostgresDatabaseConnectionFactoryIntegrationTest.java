package org.nachc.tools.fhirtoomop.util.db.connection.postgres;

import java.sql.Connection;

import org.junit.Test;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresDatabaseConnectionFactoryIntegrationTest {

	@Test
	public void shouldGetBootstrapConnection() {
		log.info("Starting test...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String sqlString = "SELECT current_user, user, session_user, current_database(), current_catalog, version()";
			Data data = Database.query(sqlString, conn);
			log.info("Current user is: " + data.get(0).get("currentUser"));
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	@Test
	public void shouldGetOhdsiConnection() {
		log.info("Starting test...");
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			String sqlString = "SELECT current_user, user, session_user, current_database(), current_catalog, version()";
			Data data = Database.query(sqlString, conn);
			log.info("Current user is: " + data.get(0).get("currentUser"));
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
