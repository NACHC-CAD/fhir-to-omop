package org.nachc.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.synthea.patient.PatientIdFetcher;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class E_GetPatientFromPatientEverythingIntegrationTest {

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		// get the oauth token
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		// get the patient id and $everything json
		String patientId = PatientIdFetcher.getASinglePatientId(token);
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String patientJson = synthea.fetchEverything(patientId, token);
		// get the patient $everything parser
		PatientEverythingParser patientParser = new PatientEverythingParser(patientJson);
		// get the id from the $everything patient
		PatientParser patient = patientParser.getPatient();
		String patientIdFromParser = patient.getId();
		log.info("Patient ID from request: " + patientId);
		log.info("Got ID from everything:  " + patientIdFromParser);
		assertTrue(patientId.equals(patientIdFromParser));
		// done
		log.info("Done.");
	}

}
