package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.omop.datafactory.OmopConceptFactory;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This test creates an OMOP PersonDvo and populates it from a Patient
 * $everything string from Synthea.
 *
 */

@Slf4j
public class OmopPersonFactoryIntegrationTest {

	@Test
	public void shouldGetOmopPerson() {
		log.info("Starting test...");
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			// get the test data and create the dvo using the factory
			String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
			OmopPersonEverythingFactory personEverythingFactory = new OmopPersonEverythingFactory(json, conn);
			PersonDvo person = personEverythingFactory.getPerson();
			// person id
			int id = person.getPersonId();
			log.info("PersonId: " + id);
			assertTrue(id > 0);
			// person source id
			String personSourceId = person.getPersonSourceValue();
			log.info("SourceId: " + personSourceId);
			assertTrue(personSourceId.equals("6f7acde5-db81-4361-82cf-886893a3280c"));
			// race
			Integer raceId = person.getRaceConceptId();
			log.info("Got race_id: " + raceId);
			ConceptDvo raceDvo = OmopConceptFactory.getConcept(raceId, conn);
			String raceFromOmop = raceDvo.getConceptName();
			log.info("raceFromOmop: " + raceFromOmop);
			assertTrue(raceFromOmop.equals("White"));
			String raceFromSource = person.getRaceSourceValue();
			log.info("raceFromSource: " + raceFromSource);
			assertTrue(raceFromSource.equals("2106-3"));
			// ethnicity
			Integer ethId = person.getEthnicityConceptId();
			log.info("Got eth_id: " + ethId);
			ConceptDvo ethDvo = OmopConceptFactory.getConcept(ethId, conn);
			log.info("ethFromOmop: " + ethDvo.getConceptName());
			assertTrue("Not Hispanic or Latino".equals(ethDvo.getConceptName()));
			String ethFromSource = person.getEthnicitySourceValue();
			log.info("ethFromSource: " + ethFromSource);
			assertTrue(ethFromSource.equals("2186-5"));
			// gender
			Integer genderId = person.getGenderConceptId();
			log.info("Got gender id: " + genderId);
			assertTrue(genderId == 8507);
			String genderFromSource = person.getGenderSourceValue();
			log.info("Got gender from source: " + genderFromSource);
			assertTrue(genderFromSource.equals("male"));
			// birth day
			Integer birthYear = person.getYearOfBirth();
			log.info("Got birth year: " + birthYear);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		// done
		log.info("Done.");
	}

}
