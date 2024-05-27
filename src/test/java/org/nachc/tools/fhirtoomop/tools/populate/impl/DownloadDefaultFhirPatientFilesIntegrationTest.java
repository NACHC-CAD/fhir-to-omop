package org.nachc.tools.fhirtoomop.tools.populate.impl;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadDefaultFhirPatientFilesIntegrationTest {

	@Test
	public void shouldDownloadFile() {
		log.info("Starting test...");
		DownloadDefaultFhirPatientFiles.exec();
		log.info("Done.");
	}
	
}
