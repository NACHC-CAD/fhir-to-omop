package org.nachc.tools.fhirtoomop.tools.build.impl;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveRaceEthFilesIntegrationTest {

	private static final String DST_DIR = "/temp/fhir-to-omop";
	
	@Test
	public void shouldMoveFiles() {
		log.info("Starting test...");
		new MoveRaceEthFiles().exec(DST_DIR);
		log.info("Done.");
	}
	
}
