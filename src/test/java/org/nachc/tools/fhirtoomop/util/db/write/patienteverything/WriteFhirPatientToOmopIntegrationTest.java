package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopIntegrationTest {

	@Test
	public void shouldWritePatientToDatabase() {
		int numberToWrite = 3;
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Getting patient...");
			String json = TestParams.getPersonEverythingJson();
			log.info("Writing to database...");
			for (int i = 0; i < numberToWrite; i++) {
				WriteFhirPatientToOmop.exec(json, conn);
			}
			log.info("Doing commit...");
			Database.commit(conn);
		} finally {
			log.info("Closing connection...");
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
