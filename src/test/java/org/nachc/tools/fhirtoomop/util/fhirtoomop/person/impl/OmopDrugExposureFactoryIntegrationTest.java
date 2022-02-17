package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDrugExposureFactoryIntegrationTest {

	@Test
	public void shouldCreateDvo() {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Starting test...");
			PatientEverythingParser patient = TestParams.getPatientEverything();
			OmopPersonEverythingFactory person = new OmopPersonEverythingFactory(patient, conn);
			List<DrugExposureDvo> dvoList = person.getDrugExposureList();
			log.info("Got " + dvoList.size() + " drug exposures");
			assertTrue(dvoList.size() == 2);
			int cnt = 0;
			for (DrugExposureDvo dvo : dvoList) {
				cnt++;
				log.info("Drug Exposure " + cnt + " of " + dvoList.size());
				log.info("\tdrugExposureId:  " + dvo.getDrugExposureId());
				assertTrue(dvo.getDrugExposureId() != null);
				log.info("\tpersonId:        " + dvo.getPersonId());
				assertTrue(dvo.getPersonId() != null);
				log.info("\tvisitOccurenceId:" + dvo.getVisitOccurrenceId());
				assertTrue(dvo.getVisitOccurrenceId() != null);
				log.info("drugConceptId:     " + dvo.getDrugConceptId());
				assertTrue(dvo.getDrugConceptId() == 1713671 || dvo.getDrugConceptId() == 19081453);
			}
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
