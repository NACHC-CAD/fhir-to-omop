package org.nachc.tools.fhirtoomop.tools.dqd;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunDqdInitScriptIntegrationTest {

	@Test
	public void shouldExecuteScript() {
		log.info("Starting test..");
		RunDqdInitScript.exec();
		log.info("Done.");
	}

}
