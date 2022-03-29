package org.nachc.tools.fhirtoomop.omop.person.factory.builder.person;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.omop.concept.OmopConceptFactory;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This test creates an OMOP PersonDvo and populates it from a Patient
 * $everything string from Synthea.
 *
 */

@Slf4j
public class OmopPersonBuilderIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetOmopPerson() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFhirPatient();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			PersonDvo dvo = omopPerson.getPerson();
			// person id
			int id = dvo.getPersonId();
			log.info("PersonId: " + id);
			assertTrue(id > 0);
			// person source id
			String personSourceId = dvo.getPersonSourceValue();
			log.info("SourceId: " + personSourceId);
			assertTrue(personSourceId.equals("5acc8bb4-2d14-4461-a560-228d96459cc3"));
			// race
			Integer raceId = dvo.getRaceConceptId();
			log.info("Got race_id: " + raceId);
			ConceptDvo raceDvo = OmopConceptFactory.getConcept(raceId, conn);
			String raceFromOmop = raceDvo.getConceptName();
			log.info("raceFromOmop: " + raceFromOmop);
			assertTrue(raceFromOmop.equals("Asian"));
			String raceFromSource = dvo.getRaceSourceValue();
			log.info("raceFromSource: " + raceFromSource);
			assertTrue(raceFromSource.equals("2028-9"));
			// ethnicity
			Integer ethId = dvo.getEthnicityConceptId();
			log.info("Got eth_id: " + ethId);
			ConceptDvo ethDvo = OmopConceptFactory.getConcept(ethId, conn);
			log.info("ethFromOmop: " + ethDvo.getConceptName());
			assertTrue("Not Hispanic or Latino".equals(ethDvo.getConceptName()));
			String ethFromSource = dvo.getEthnicitySourceValue();
			log.info("ethFromSource: " + ethFromSource);
			assertTrue(ethFromSource.equals("2186-5"));
			// gender
			Integer genderId = dvo.getGenderConceptId();
			log.info("Got gender id: " + genderId);
			assertTrue(genderId == 8532);
			String genderFromSource = dvo.getGenderSourceValue();
			log.info("Got gender from source: " + genderFromSource);
			assertTrue(genderFromSource.equals("female"));
			// birth year/month/day
			Integer birthYear = dvo.getYearOfBirth();
			Integer birthMonth = dvo.getMonthOfBirth();
			Integer birthDay = dvo.getDayOfBirth();
			log.info("Got birth year: " + birthYear + "-" + birthMonth + "-" + birthDay);
			assertTrue(birthYear == 1983);
			assertTrue(birthMonth == 2);
			assertTrue(birthDay == 3);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		// done
		log.info("Done.");
	}

}
