package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything;

import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class implements the $everything for the Patient resource for the
 * synthea server.
 *
 */

@Slf4j
public class SyntheaPatientEverythingFetcher {

	private HttpRequestClient client;

	public HttpRequestClient getClient() {
		return this.client;
	}

	public int getStatusCode() {
		return this.client.getStatusCode();
	}

	public String fetchEverything(String patientId) {
		String url = SyntheaParams.getUrl();
		String key = SyntheaParams.getKey();
		url += "/Patient/" + patientId + "/$everything?";
		url += "apikey=" + key;
		log.info("URL: " + url);
		this.client = new HttpRequestClient(url);
		client.doGet();
		int status = client.getStatusCode();
		log.info("Got status: " + status);
		String response = client.getResponse();
		log.info("Response length: " + response.length());
		return response;
	}

}
