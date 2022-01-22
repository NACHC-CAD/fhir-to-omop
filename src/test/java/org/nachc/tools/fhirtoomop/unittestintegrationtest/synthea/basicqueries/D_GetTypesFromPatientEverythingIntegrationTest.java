package org.nachc.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.synthea.patient.PatientIdFetcher;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class D_GetTypesFromPatientEverythingIntegrationTest {

	@Test
	public void shouldGetTypes() {
		log.info("Starting test...");
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		String patientId = PatientIdFetcher.getASinglePatientId(token);
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String patientJson = synthea.fetchEverything(patientId, token);
		PatientEverythingParser patient = new PatientEverythingParser(patientJson);
		List<String> types = patient.getResourceTypes();
		log.info("Got " + types.size() + " types.");
		for (String type : types) {
			log.info("\t" + type);
		}
		log.info("Got " + types.size() + " types.");
		assertTrue(types.size() > 5);
		log.info("Done.");
	}

}
