package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGeneratorIntegrationTest {

	@Test
	public void shouldGetId() {
		log.info("Starting test...");
		Integer id = FhirToOmopIdGenerator.getId("person", "person_id");
		log.info("Got ID: " + id);
		assertTrue(id != null);
		log.info("Done.");
	}

}
