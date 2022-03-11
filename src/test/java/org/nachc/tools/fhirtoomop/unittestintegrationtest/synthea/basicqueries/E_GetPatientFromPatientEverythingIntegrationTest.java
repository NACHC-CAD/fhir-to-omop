package org.nachc.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;
import org.nachc.tools.synthea.patient.PatientIdFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class E_GetPatientFromPatientEverythingIntegrationTest {

	private static final int MAX_TRIES = 10;
	
	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		int cnt = 0;
		while(true) {
			cnt++;
			log.info("ATTEMPT " + cnt + " OF " + MAX_TRIES);
			// sometimes we get an error so we try a few times before we give up
			if(cnt >= MAX_TRIES) {
				break;
			}
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
			if(patientIdFromParser == null) {
				continue;
			} else {
				log.info("Patient ID from request: " + patientId);
				log.info("Got ID from everything:  " + patientIdFromParser);
				assertTrue(patientId.equals(patientIdFromParser));
				break;
			}
		}
		// done
		log.info("Done.");
	}

}
