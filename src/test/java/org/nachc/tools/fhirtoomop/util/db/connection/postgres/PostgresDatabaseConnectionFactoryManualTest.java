package org.nachc.tools.fhirtoomop.util.db.connection.postgres;

import java.sql.Connection;

import org.junit.Test;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresDatabaseConnectionFactoryManualTest {

	@Test
	public void shouldGetConnections() {
		log.info("Starting test...");
		getBootstrapConnection();
		// these only work for postgresql full set up for atlas
//		shouldGetOhdsiConnection();
//		shouldGetOhdsiDb();
		log.info("Done.");
	}
	
	public void getBootstrapConnection() {
		log.info("Getting Bootstrap Connection...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String sqlString = "SELECT current_user, user, session_user, current_database(), current_catalog, version()";
			Data data = Database.query(sqlString, conn);
			log.info("Current user is: " + data.get(0).get("currentUser"));
		} finally {
			Database.close(conn);
		}
		log.info("Done testing bootstrap connection.");
	}

	public void shouldGetOhdsiConnection() {
		log.info("Getting ohdsi Connection...");
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			String sqlString = "SELECT current_user, user, session_user, current_database(), current_catalog, version()";
			Data data = Database.query(sqlString, conn);
			log.info("Current user is: " + data.get(0).get("currentUser"));
		} finally {
			Database.close(conn);
		}
		log.info("Done testing ohdsi connection.");
	}

}
