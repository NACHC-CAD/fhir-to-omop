package org.nachc.tools.fhirtoomop.tools.download.authenticate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirServerAuthenticatorIntegrationTest {

	@Test
	public void shouldAuthenticateRequest() {
		log.info("Starting test..");
		String url = AppParams.get("fhirPatientServerUrl");
		url = url + "/Patient";
		HttpRequestClient client = new HttpRequestClient(url);
		FhirServerAuthenticator.addAuthentication(client);
		client.doGet();
		String response = client.getResponse();
		response = JsonUtil.prettyPrint(response);
		log.info("Got response: \n" + response);
		BundleParser bundle = new BundleParser(response);
		String nextUrl = bundle.getNextUrl();
		log.info("Got next url: " + nextUrl);
		assertTrue(nextUrl != null);
		log.info("Done.");
	}

}
