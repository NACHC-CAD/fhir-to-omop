package org.nachc.tools.fhirtoomop.util.params;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParamsIntegrationTest {

	
	@Test
	public void shouldGetParams() {
		log.info("Starting test...");
		AppParams.touch();
		log.info("Done.");
	}
}
