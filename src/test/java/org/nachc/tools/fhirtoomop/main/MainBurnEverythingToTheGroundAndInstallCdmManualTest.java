package org.nachc.tools.fhirtoomop.main;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainBurnEverythingToTheGroundAndInstallCdmManualTest {

	private static final String[] args = { "i" };

	@Test
	public void shouldUploadPatients() throws Exception {
		log.info("Starting test...");
		FhirToOmopMain.main(args);
		log.info("Done.");
	}

}
