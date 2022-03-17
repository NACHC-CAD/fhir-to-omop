package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything;

import org.nachc.tools.fhirtoomop.util.fhir.everything.FhirPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class implements the $everything for the Patient resource for the
 * synthea server.
 *
 */

@Slf4j
public class SyntheaPatientEverythingFetcher {

	private FhirPatientEverythingFetcher fetcher;

	public SyntheaPatientEverythingFetcher() {
		this.fetcher = new FhirPatientEverythingFetcher();
	}
	
	public HttpRequestClient getClient() {
		return this.fetcher.getClient();
	}

	public int getStatusCode() {
		return this.fetcher.getClient().getStatusCode();
	}

	public String fetchEverything(String patientId, String token) {
		String url = AppParams.getSyntheaUrl();
		String response = fetcher.fetchEverything(url, patientId, token);
		return response;
	}

}
