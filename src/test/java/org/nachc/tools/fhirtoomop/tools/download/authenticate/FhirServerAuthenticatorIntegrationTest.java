package org.nachc.tools.fhirtoomop.tools.download.authenticate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.util.server.auth.HttpClientAuthenticator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirServerAuthenticatorIntegrationTest {

	@Test
	public void placeHolder() {
		// TODO: SYNTHEA HAS BEEN SHUT DOWN :(
		log.info("PLACE HOLDER: SYNTHEA HAS BEEN SHUT DOWN, NEED TO FIND NEW EXAMPLE.");
	}
	
	public void shouldAuthenticateRequest() {
		log.info("Starting test..");
		String url = AppParams.getFhirPatientServerUrl();
		url = url + "/Patient";
		// auth after init and after refresh
		HttpRequestClient client;
		String response;
		BundleParser bundle;
		String nextUrl;
		// auth after init
		client = new HttpRequestClient(url);
		FhirServerAuthenticator.auth(client);
		client.doGet();
		response = client.getResponse();
		response = JsonUtil.prettyPrint(response);
		log.info("Got response: \n" + response);
		bundle = new BundleParser(response);
		nextUrl = bundle.getNextUrl();
		log.info("Got next url: " + nextUrl);
		assertTrue(nextUrl != null);
		// auth after refresh
		FhirServerAuthenticator.refresh();
		client = new HttpRequestClient(url);
		FhirServerAuthenticator.auth(client);
		client.doGet();
		response = client.getResponse();
		response = JsonUtil.prettyPrint(response);
		log.info("Got response: \n" + response);
		bundle = new BundleParser(response);
		nextUrl = bundle.getNextUrl();
		log.info("Got next url: " + nextUrl);
		assertTrue(nextUrl != null);
		// done
		log.info("Done.");
	}

}
