package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class implements the ability to fetch a list of patient summaries from
 * the synthia fhir server.
 *
 */

@Slf4j
public class SyntheaPatientSummaryListFetcher {

	private HttpRequestClient client;

	public HttpRequestClient getClient() {
		return this.client;
	}

	public int getStatusCode() {
		return this.client.getStatusCode();
	}

	public String fetchPatients(int howMany) {
		String url = SyntheaParams.getUrl();
		String key = SyntheaParams.getKey();
		url += "/Patient?";
		url += "_count=" + howMany;
		url += "&apikey=" + key;
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
