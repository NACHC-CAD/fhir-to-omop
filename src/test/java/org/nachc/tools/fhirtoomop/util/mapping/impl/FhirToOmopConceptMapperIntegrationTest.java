package org.nachc.tools.fhirtoomop.util.mapping.impl;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopConceptMapperIntegrationTest {

	@Test
	public void shouldGetConcept() {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Starting test...");
			String system = "http://loinc.org";
			String code = "33914-3";
			ConceptDvo dvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(system, code, conn);
			log.info("Got dvo: " + dvo.getConceptName());
		} finally {
			log.info("Closing connection");
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}
