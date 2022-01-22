package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaPatientSummaryListFetcherIntegrationTest {

	private static final int CNT = 5;

	@Test
	public void shouldFetchPatients() {
		// get patients from synthea
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(CNT, token);
		List<PatientSummaryParser> patientList = synthea.getPatients();
		log.info("Got " + patientList.size() + " patients.");
		for (PatientSummaryParser parser : patientList) {
			String patientId = parser.getId();
			log.info("\t" + patientId);
			assertTrue(patientId != null);
		}
		log.info("Got " + patientList.size() + " patients.");
		assertTrue(patientList.size() == CNT);
		// get the next url
		String nextUrl = synthea.getNextUrl();
		log.info("Got next url: \n" + nextUrl);
		assertTrue(nextUrl.startsWith("https://syntheticmass.mitre.org/v1/fhir/Patient/?"));
		// get the next set of patients
		synthea = synthea.fetchNext(CNT, token);
		List<PatientSummaryParser> nextPatientList = synthea.getPatients();
		log.info("Got " + nextPatientList.size() + " patients.");
		for (PatientSummaryParser parser : nextPatientList) {
			String patientId = parser.getId();
			log.info("\t" + patientId);
			assertTrue(patientId != null);
		}
		log.info("Got " + nextPatientList.size() + " patients.");
		assertTrue(patientList.size() == CNT);
		log.info("Done.");
	}

}
