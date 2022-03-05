package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopIntegrationTest {

	public static final int NUMBER_TO_WRITE = 100;
	
	@Test
	public void shouldWritePatientToDatabase() {
		log.info("Starting test...");
		Timer timer = new Timer();
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Getting patient...");
			String json = TestParams.getPersonEverythingJson();
			log.info("Writing to database...");
			timer.start();
			for (int i = 0; i < NUMBER_TO_WRITE; i++) {
				WriteFhirPatientToOmop.exec(json, conn);
				log.info("Done writing patient " + (i+1) + " of " + NUMBER_TO_WRITE);
			}
			timer.stop();
			log.info("Doing commit...");
			Database.commit(conn);
		} finally {
			log.info("Closing connection...");
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("---");
		log.info("start:   " + timer.getStart());
		log.info("stop:    " + timer.getStop());
		log.info("elapsed: " + timer.getElapsedString());
		log.info("---");
		log.info("Done.");
	}

}
