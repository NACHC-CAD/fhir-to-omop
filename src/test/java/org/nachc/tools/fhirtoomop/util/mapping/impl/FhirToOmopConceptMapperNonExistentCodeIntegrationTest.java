package org.nachc.tools.fhirtoomop.util.mapping.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopConceptMapperNonExistentCodeIntegrationTest {

	@Test
	public void shouldGetConcept() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Starting test...");
			String system = "foo";
			String code = "bar";
			ConceptDvo dvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(system, code, conn);
			log.info("Got dvo: " + dvo.getConceptName());
			log.info("Got id:  " + dvo.getConceptId());
			assertTrue(dvo.getConceptId() > 1000000000);
		} finally {
			log.info("Closing connection");
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
