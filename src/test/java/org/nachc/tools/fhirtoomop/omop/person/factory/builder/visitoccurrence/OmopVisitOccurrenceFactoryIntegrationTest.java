package org.nachc.tools.fhirtoomop.omop.person.factory.builder.visitoccurrence;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class tests that the visit occurrences are created correctly by the OMOP
 * factory classes.
 *
 */

@Slf4j
public class OmopVisitOccurrenceFactoryIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetVisitOccurrences() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
		FhirPatient fhirPatient = new FhirPatientFactory(resources).buildFromFileList();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			// create the omop person
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			List<VisitOccurrenceDvo> visitList = omopPerson.getVisitOccurrenceList();
			log.info("Got " + visitList.size() + " visits.");
			assertTrue(visitList.size() == 51);
			// get a visit occurrence to test
			VisitOccurrenceDvo dvo = visitList.get(5);
			// visitId
			Integer visitId = dvo.getVisitOccurrenceId();
			log.info("visitId: " + visitId);
			assertTrue(visitId != null);
			// visitSource
			String sourceValue = dvo.getVisitSourceValue();
			log.info("visitSource: " + sourceValue);
			assertTrue(sourceValue.equals("1c25ad88-4804-428d-a911-7163105d10b7"));
			// patientId
			Integer personId = dvo.getPersonId();
			log.info("personId: " + personId);
			assertTrue(personId != null);
			// startDate
			Date startDate = dvo.getVisitStartDate();
			String startDateString = TimeUtil.format(startDate, "yyyy-MM-dd");
			log.info("startDate: " + startDateString);
			assertTrue(startDateString.equals("2013-08-29"));
			// endDate
			Date endDate = dvo.getVisitEndDate();
			String endDateString = TimeUtil.format(endDate, "yyyy-MM-dd");
			log.info("endDate:   " + endDateString);
			assertTrue(endDateString.equals("2013-08-29"));
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
