package org.nachc.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class B_GetListOfPatientSummariesIntegrationTest {

	@Test
	public void shouldGetPatients() {
		log.info("Starting test..");
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher();
		String response = synthea.fetchPatients(5);
		log.info("Got response (length):" + response.length());
		log.info("Note: if you fetch 10 or more patients here the response does not appear in the output in eclipse(but you can cut and paste the response into a text file)");
		log.info("Response:\n" + response);
		log.info("Status code: " + synthea.getStatusCode());
		assertTrue(synthea.getStatusCode() == 200);
		log.info("Done.");
	}

}
