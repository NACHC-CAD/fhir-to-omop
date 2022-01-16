package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class tests that the visit occurrences are created correctly by the OMOP
 * factory classes.
 *
 */

@Slf4j
public class OmopVisitOccurrenceFactoryIntegrationTest {

	@Test
	public void shouldGetVisitOccurrences() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			PatientEverythingParser patient = TestParams.getPatientEverything();
			OmopPersonEverythingFactory omopParser = new OmopPersonEverythingFactory(patient, conn);
			List<VisitOccurrenceDvo> visitList = omopParser.getVisitOccurrenceList();
			assertTrue(visitList.size() == 10);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
