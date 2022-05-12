package org.nachc.tools.fhirtoomop.tools.build.impl;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveRaceEthFilesIntegrationTest {

	@Test
	public void shouldMoveFiles() {
		log.info("Starting test...");
		new MoveRaceEthFiles().exec();
		log.info("Done.");
	}
	
}
