package org.nachc.tools.fhirtoomop.util.mapping;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionMappingIntegrationTest {

	@Test
	public void shouldGetMapping() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			Coding coding = new Coding();
			coding.setCode("75498004");
			coding.setSystem("http://snomed.info/sct");
			ConceptDvo dvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(coding, conn);
			log.info("Got concept: " + dvo);
			log.info("ID: " + dvo.getConceptId());
			log.info("name: " + dvo.getConceptName());
			assertTrue(dvo.getConceptId() == 4294548);
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
