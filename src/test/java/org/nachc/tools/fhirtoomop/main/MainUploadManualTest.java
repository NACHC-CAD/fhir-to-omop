package org.nachc.tools.fhirtoomop.main;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainUploadManualTest {

	private static final String[] args = { "u" };

	@Test
	public void shouldUploadPatients() throws Exception {
		log.info("Starting test...");
		FhirToOmopMain.main(args);
		log.info("Done.");
	}

}
