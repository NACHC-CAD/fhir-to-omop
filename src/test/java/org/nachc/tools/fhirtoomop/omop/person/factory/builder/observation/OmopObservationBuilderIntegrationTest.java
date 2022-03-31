package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopObservationBuilderIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetDvo() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFromFileList();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			OmopPerson person = new OmopPersonFactory().build(fhirPatient, conn);
			List<ObservationDvo> obsList = person.getObservationList();
			List<MeasurementDvo> measList = person.getMeasurementList();
			String patientId = person.getPerson().getPersonSourceValue();
			log.info("Patient ID: " + patientId);
			log.info("Got " + obsList.size() + " observations.");
			assertTrue(obsList.size() == 6);
			log.info("Got " + measList.size() + " measurements.");
			assertTrue(measList.size() == 116);
			// test a single dvo
			ObservationDvo dvo;
			dvo = obsList.get(0);
			log.info("obsId: " + dvo.getObservationId());
			assertTrue(dvo.getObservationId() != null);
			log.info("obsConceptId: " + dvo.getObservationConceptId());
			assertTrue(43054909 == dvo.getObservationConceptId());
			// get the 14th dvo (it has a value that is not a coding or number)
			dvo = obsList.get(0);
			log.info("Got obs 0: " + dvo.getObservationSourceValue());
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
