package org.nachc.tools.fhirtoomopexamples.fhir.omoppersoneverything;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.unittesttools.fhir.synthea.patient.GetASingleSyntheaPatient;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingExampleIntegrationTest {
	
	@Test
	public void shouldCreateOmopPersonEverything() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			List<String> patient = TestParams.getTestPatientAsFileList();
			OmopPersonEverything person = OmopPersonEverythingFactory.makePerson(patient, conn);
			log.info("Got person");
			echoDetails(person);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	private void echoDetails(OmopPersonEverything person) {
		log.info("Person details: ");
		// get some example data
		PersonDvo personDvo = person.getPerson();
		List<VisitOccurrenceDvo> visitList = person.getVisitOccurrenceList();
		List<MeasurementDvo> measurementList = person.getMeasurementList();
		// log the person details
		log.info("---------------------");
		log.info("Got person: ");
		log.info("---------------------");
		log.info("PersonId:        " +  personDvo.getPersonId());
		log.info("PersonSourceId:  " + personDvo.getPersonSourceValue());
		log.info("Race:            " + personDvo.getRaceSourceValue());
		log.info("Race Concept Id: " + personDvo.getRaceConceptId());
		log.info("Got " + visitList.size() + " visits");
		log.info("Got " + measurementList.size() + " measurements");
		// etc...
	}
	
}
