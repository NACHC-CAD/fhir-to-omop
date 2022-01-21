package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaPatientSummaryListFetcherIntegrationTest {

	private static final int CNT = 5;

	@Test
	public void shouldFetchPatients() {
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher();
		List<PatientSummaryParser> patientList = synthea.fetchPatientSummaryParsers(CNT);
		log.info("Got " + patientList.size() + " patients.");
		for (PatientSummaryParser parser : patientList) {
			String patientId = parser.getId();
			log.info("\t" + patientId);
			assertTrue(patientId != null);
		}
		log.info("Got " + patientList.size() + " patients.");
		assertTrue(patientList.size() == CNT);
		log.info("Done.");
	}

}
