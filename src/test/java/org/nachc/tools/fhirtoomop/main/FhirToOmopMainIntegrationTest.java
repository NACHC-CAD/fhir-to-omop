package org.nachc.tools.fhirtoomop.main;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopMainIntegrationTest {

	@Test
	public void shouldRunMain() throws Exception {
		try {
			String[] args = new String[1];
			log.info("Starting test...");
			args[0] = "h";
			FhirToOmopMain.main(args);
			args[0] = "m";
			FhirToOmopMain.main(args);
			args[0] = "sadfasdfasdfagqeradfv/\\<>q4???t\"q~tv43453tdgndfgdgfhjfk`";
			FhirToOmopMain.main(args);
		} finally {
			AppParams.resetToDefault();
		}
		log.info("Done.");
	}

}
