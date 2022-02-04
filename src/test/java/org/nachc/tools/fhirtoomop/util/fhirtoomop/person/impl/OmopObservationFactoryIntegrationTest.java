package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationDvoHelper;
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
			assertTrue(obsList.size() == 50);
			// show all obs
			log.info("\t" + ObservationDvoHelper.getFixedWithHeaderRow());
			for(ObservationDvo dvo : obsList) {
				ObservationDvoHelper helper = new ObservationDvoHelper(dvo, conn);
				log.info("\t" + helper.getAsFixedWidthString());
			}
			// test a single dvo
			ObservationDvo dvo;
			dvo = obsList.get(0);
			log.info("obsId: " + dvo.getObservationId());
			assertTrue(dvo.getObservationId() != null);
			log.info("obsConceptId: " + dvo.getObservationConceptId());
			assertTrue(43055141 == dvo.getObservationConceptId());
			// get the 14th dvo (it has a value that is not a coding or number)
			dvo = obsList.get(15);
			log.info("Got obs 15: " + dvo.getObservationSourceValue());
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
