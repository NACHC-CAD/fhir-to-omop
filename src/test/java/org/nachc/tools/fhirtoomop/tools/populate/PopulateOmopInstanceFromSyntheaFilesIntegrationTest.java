package org.nachc.tools.fhirtoomop.tools.populate;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateOmopInstanceFromSyntheaFilesIntegrationTest {

	@Test
	public void shouldUploadSyntheaPatients() {
		log.info("Starting test...");
		PopulateOmopInstanceFromSyntheaFiles.main(null);
		log.info("Done.");
	}
	
}
