package org.nachc.cosmos.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.cosmos.tools.fhirtoomop.unittesttool.synthea.patient.PatientIdFetcher;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class E_GetPatientIntegrationTest {

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		String patientId = PatientIdFetcher.getASinglePatientId();
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String patientJson = synthea.fetchEverything(patientId);
		PatientEverythingParser patientParser = new PatientEverythingParser(patientJson);
		Patient fhirPatient = patientParser.getPatient();
		log.info("Got Patient: " + fhirPatient);
		assertTrue(fhirPatient != null);
		PatientParser patient = new PatientParser(fhirPatient);
		String patientIdFromParser = patient.getId();
		log.info("Patient ID from request: " + patientId);
		log.info("Got ID from everything:  " + patientIdFromParser);
		assertTrue(patientId.equals(patientIdFromParser));
		log.info("Done.");
	}
	
}
