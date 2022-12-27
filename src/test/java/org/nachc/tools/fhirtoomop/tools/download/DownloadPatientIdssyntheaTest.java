package org.nachc.tools.fhirtoomop.tools.download;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatientIdssyntheaTest {

	private static final int MAX_PATIENTS_TO_GET = 20;

	@Test
	public void shouldGetPatientIds() {
		log.info("Starting test...");
		DownloadPatientIds.exec(MAX_PATIENTS_TO_GET);
		log.info("Done.");
	}

}
