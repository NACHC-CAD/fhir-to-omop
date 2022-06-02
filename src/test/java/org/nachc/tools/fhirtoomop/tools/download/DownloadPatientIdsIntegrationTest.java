package org.nachc.tools.fhirtoomop.tools.download;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatientIdsIntegrationTest {

	private static final int MAX_PATIENTS_TO_GET = 200;

	@Test
	public void placeHolder() {
		// TODO: SYNTHEA HAS BEEN SHUT DOWN :(
		log.info("PLACE HOLDER: SYNTHEA HAS BEEN SHUT DOWN, NEED TO FIND NEW EXAMPLE.");
	}
	
	public void shouldGetPatientIds() {
		log.info("Starting test...");
		DownloadPatientIds.exec(MAX_PATIENTS_TO_GET);
		log.info("Done.");
	}

}
