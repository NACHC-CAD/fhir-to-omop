package org.nachc.tools.fhirtoomopexamples.fhir.oauth;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaOauthIntegrationTest {

	private static final String URL = "https://syntheticmass.mitre.org/v1/fhir/Patient";
	
	
	@Test
	public void shouldGetToken() {
		log.info("Starting test to get token...");
		String token = SyntheaOauth.fetchToken();
		log.info("Got token: " + token);
		log.info("Done.");
	}
	
	@Test
	public void requestUsingTokenShouldBeValid() {
		log.info("Starting test to use token...");
		String token = SyntheaOauth.fetchToken();
		HttpRequestClient client = new HttpRequestClient(URL);
		SyntheaOauth.addHeaders(client, token);
		client.doGet();
		String response = client.getResponse();
		response = JsonUtil.prettyPrint(response);
		log.info("\n\nGot response: \n" + response + "\n\n");
		log.info("Done.");
	}
}
