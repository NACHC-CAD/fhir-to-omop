package org.nachc.tools.fhirtoomop.tools.test;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteTestPatientsToDatabaseIntegrationTest {

	@Test
	public void shouldWritePatients() {
		log.info("Starting test...");
		WriteTestPatientsToDatabase.exec(3);
		log.info("Done");
	}
	
}
