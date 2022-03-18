package org.nachc.tools.fhirtoomopexamples.omop;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.tools.populate.PopulateFromFiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFromFilesExampleIntegrationTest {

	@Test
	public void shouldWritePatients() {
		log.info("Starting test...");
		PopulateFromFiles.main(null);
		log.info("Done.");
	}
	
}
