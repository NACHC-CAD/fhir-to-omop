package org.nachc.tools.fhirtoomopexamples.synthea;

import org.junit.Test;
import org.nachc.tools.synthea.allpatientids.GetAllSyntheaPatientIdsTool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientIdsExampleIntegrationTest {

	private static final int MAX_PATIENTS_TO_GET = 2500;
	
	@Test
	public void shouldGetPatientIds() {
		log.info("Starting test...");
		GetAllSyntheaPatientIdsTool.exec(MAX_PATIENTS_TO_GET);
		log.info("Done.");
	}
	
}
