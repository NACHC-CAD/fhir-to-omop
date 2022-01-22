package org.nachc.tools.fhirtoomop.util.synthea.fetcher.token;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaTokenFetcherIntegrationTest {

	@Test
	public void shouldGetToken() {
		log.info("Starting test...");
		String token = SyntheaTokenFetcher.fetchToken();
		log.info("Got token: " + token);
		log.info("Done.");
	}

}
