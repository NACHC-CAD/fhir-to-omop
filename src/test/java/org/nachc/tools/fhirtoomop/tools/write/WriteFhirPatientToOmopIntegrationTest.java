package org.nachc.tools.fhirtoomop.tools.write;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopIntegrationTest {

	@Test
	public void shouldWritePatientToDatabase() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Getting patient...");
			String json = TestParams.getPersonEverythingJson();
			log.info("Writing to database...");
			WriteFhirPatientToOmop.exec(json, conn);
			log.info("Doing commit...");
			Database.commit(conn);
		} finally {
			log.info("Closing connection...");
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}
