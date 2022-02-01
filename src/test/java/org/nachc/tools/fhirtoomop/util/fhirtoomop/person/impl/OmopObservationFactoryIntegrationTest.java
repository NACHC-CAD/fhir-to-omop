package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopObservationFactoryIntegrationTest {

	@Test
	public void shouldGetDvo() {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Starting test...");
			// get the person and get the list
			PatientEverythingParser patient = TestParams.getPatientEverything();
			OmopPersonEverythingFactory person = new OmopPersonEverythingFactory(patient, conn);
			List<ObservationDvo> obsList = person.getObservationList();
			log.info("Got " + obsList.size() + " observations.");
			assertTrue(obsList.size() == 45);
			// show all obs
			log.info("\tID\tCONCEPT_ID\tSTRING\tNUMBER");
			for(ObservationDvo dvo : obsList) {
				log.info("\t" + dvo.getObservationId() + "\t" + dvo.getValueAsConceptId() + "\t" + dvo.getValueAsString() + "\t" + dvo.getValueAsNumber());
			}
			// test a single dvo
			ObservationDvo dvo = obsList.get(0);
			log.info("obsId: " + dvo.getObservationId());
			assertTrue(dvo.getObservationId() != null);
			log.info("obsConceptId: " + dvo.getObservationConceptId());
			assertTrue(43055141 == dvo.getObservationConceptId());
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
