package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.database.Database;
import org.yaorma.util.time.TimeUtil;

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
			// get the visit occurrences
			PatientEverythingParser patient = TestParams.getPatientEverything();
			OmopPersonEverythingFactory omopParser = new OmopPersonEverythingFactory(patient, conn);
			List<VisitOccurrenceDvo> visitList = omopParser.getVisitOccurrenceList();
			assertTrue(visitList.size() == 10);
			// get a visit occurrence to test
			VisitOccurrenceDvo dvo = visitList.get(5);
			// visitId
			Integer visitId = dvo.getVisitOccurrenceId();
			log.info("visitId: " + visitId);
			assertTrue(visitId != null);
			// visitSource
			String sourceValue = dvo.getVisitSourceValue();
			log.info("visitSource: " + sourceValue);
			assertTrue(sourceValue.equals("64316fcb-2a6c-4f83-a93e-becf3d9c72bb"));
			// patientId
			Integer personId = dvo.getPersonId();
			log.info("personId: " + personId);
			assertTrue(personId != null);
			// startDate
			Date startDate = dvo.getVisitStartDate();
			String startDateString = TimeUtil.format(startDate, "yyyy-MM-dd");
			log.info("startDate: " + startDateString);
			assertTrue(startDateString.equals("2018-09-21"));
			// endDate
			Date endDate = dvo.getVisitEndDate();
			String endDateString = TimeUtil.format(endDate, "yyyy-MM-dd");
			log.info("endDate:   " + endDateString);
			assertTrue(endDateString.equals("2018-09-21"));
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
