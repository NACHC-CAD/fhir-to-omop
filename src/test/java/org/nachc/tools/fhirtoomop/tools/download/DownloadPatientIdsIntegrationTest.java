package org.nachc.tools.fhirtoomop.tools.download;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatientIdsIntegrationTest {

	private static final int MAX_PATIENTS_TO_GET = 2500;

	@Test
	public void shouldGetPatientIds() {
		log.info("Starting test...");
		DownloadPatientIds.exec(MAX_PATIENTS_TO_GET);
		log.info("Done.");
	}

}
