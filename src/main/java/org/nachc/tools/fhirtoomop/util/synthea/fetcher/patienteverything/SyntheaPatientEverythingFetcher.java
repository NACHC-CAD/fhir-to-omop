package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything;

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

	private HttpRequestClient client;

	public HttpRequestClient getClient() {
		return this.client;
	}

	public int getStatusCode() {
		return this.client.getStatusCode();
	}

	public String fetchEverything(String patientId, String token) {
		String url = AppParams.getSyntheaUrl();
		url += "/Patient/" + patientId + "/$everything?";
		log.info("URL: " + url);
		this.client = new HttpRequestClient(url);
		SyntheaOauth.addHeaders(client, token);
		client.doGet();
		int status = client.getStatusCode();
		log.info("Got status: " + status);
		String response = client.getResponse();
		log.info("Response length: " + response.length());
		if (status != 200) {
			log.info("DID NOT GET 200 STATUS: ");
			log.info(JsonUtil.prettyPrint(response));
		}
		return response;
	}

}
