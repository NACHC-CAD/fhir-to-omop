package org.nachc.tools.fhirtoomop.tools.download.patient.fetcher;

import org.nachc.tools.fhirtoomop.fhir.util.server.auth.HttpClientAuthenticator;
import org.nachc.tools.fhirtoomop.tools.download.authenticate.FhirServerAuthenticator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * This class just gets the first page for a /Patient/[ID]/$everything request.
 *
 */
@Slf4j
public class FhirPatientEverythingFetcher {

	private HttpRequestClient client;

	public HttpRequestClient getClient() {
		return this.client;
	}

	public int getStatusCode() {
		return this.client.getStatusCode();
	}

	public String fetchEverything(String patientId) {
		String url = AppParams.get("fhirPatientServerUrl");
		url += "/Patient/" + patientId + "/$everything?";
		log.info("URL: " + url);
		this.client = new HttpRequestClient(url);
		FhirServerAuthenticator.auth(client);
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
