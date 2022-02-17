package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.InsertSinglePatient;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGeneratorIntegrationTest {

	private static final int NUMBER_OF_PATIENTS_TO_INSERT = 5;

	@Test
	public void shouldGetId() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Starting test...");
			Integer originalId = FhirToOmopIdGenerator.getId("person", "person_id", conn);
			log.info("Got ID: " + originalId);
			assertTrue(originalId != null);
			for (int i = 0; i < NUMBER_OF_PATIENTS_TO_INSERT; i++) {
				log.info("\tinserting patient " + i + " of " + NUMBER_OF_PATIENTS_TO_INSERT);
				InsertSinglePatient.exec(conn);
			}
			// invalidate the key as new patients have been added to the database
			FhirToOmopIdGenerator.invalidateKey("person", "person_id");
			Integer newId = FhirToOmopIdGenerator.getId("person", "person_id", conn);
			log.info("originalId: " + originalId);
			log.info("newId:      " + newId);
			assertTrue(newId > originalId);
			for (int i = 0; i < NUMBER_OF_PATIENTS_TO_INSERT; i++) {
				log.info("\tinserting patient " + i + " of " + NUMBER_OF_PATIENTS_TO_INSERT);
				InsertSinglePatient.exec(conn);
			}
			FhirToOmopIdGenerator.invalidateAllKeys();
			Integer newestId = FhirToOmopIdGenerator.getId("person", "person_id", conn);
			log.info("originalId: " + originalId);
			log.info("newId:      " + newId);
			log.info("newestId:   " + newestId);
			assertTrue(newId > originalId && newestId > newId);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
