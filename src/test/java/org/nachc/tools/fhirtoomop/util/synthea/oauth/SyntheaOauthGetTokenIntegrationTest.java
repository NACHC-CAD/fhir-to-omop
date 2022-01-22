package org.nachc.tools.fhirtoomop.util.synthea.oauth;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaOauthGetTokenIntegrationTest {

	@Test
	public void shouldGetToken() {
		log.info("Starting test...");
		String token = SyntheaOauth.fetchToken();
		log.info("Got token: " + token);
		log.info("Done.");
	}

}
