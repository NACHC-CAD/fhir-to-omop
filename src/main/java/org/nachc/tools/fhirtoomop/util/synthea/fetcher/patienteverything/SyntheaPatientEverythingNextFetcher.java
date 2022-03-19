package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything;

import org.nachc.tools.fhirtoomop.util.fhir.everything.FhirPatientEverythingNextFetcher;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class implements the $everything for the Patient resource for the
 * synthea server.
 *
 */

@Slf4j
public class SyntheaPatientEverythingNextFetcher {

	private FhirPatientEverythingNextFetcher fetcher;

	public SyntheaPatientEverythingNextFetcher() {
		this.fetcher = new FhirPatientEverythingNextFetcher();
	}

	public HttpRequestClient getClient() {
		return this.fetcher.getClient();
	}

	public int getStatusCode() {
		return this.fetcher.getClient().getStatusCode();
	}

	public String fetchNext(String url, String token) {
		String response = fetcher.fetchNext(url, token);
		return response;
	}

}
