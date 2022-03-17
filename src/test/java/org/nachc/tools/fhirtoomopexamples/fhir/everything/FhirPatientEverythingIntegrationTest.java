package org.nachc.tools.fhirtoomopexamples.fhir.everything;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.everything.FhirPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirPatientEverythingIntegrationTest {

	private static final String URL = "https://syntheticmass.mitre.org/v1/fhir";
	
	private static final String PATIENT_ID = "6f7acde5-db81-4361-82cf-886893a3280c";
	
	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		// get an oauth token
		log.info("Getting token");
		String token = SyntheaOauth.fetchToken();
		// get the patient
		log.info("Getting Patient/$everything");
		FhirPatientEverythingFetcher fetcher = new FhirPatientEverythingFetcher();
		String patientEverythingString = fetcher.fetchEverything(URL, PATIENT_ID, token);
		// pretty print and echo back the response
		patientEverythingString = JsonUtil.prettyPrint(patientEverythingString);
		log.info("Got Patient: \n\n" + patientEverythingString + "\n\n");
		log.info("Done.");
	}

}
