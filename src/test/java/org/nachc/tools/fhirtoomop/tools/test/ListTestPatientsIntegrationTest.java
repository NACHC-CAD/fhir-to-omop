package org.nachc.tools.fhirtoomop.tools.test;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListTestPatientsIntegrationTest {

	@Test
	public void shouldListFiles() {
		ListTestPatients.exec();
	}

}
