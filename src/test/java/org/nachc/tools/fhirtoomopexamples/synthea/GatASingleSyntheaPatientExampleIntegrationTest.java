package org.nachc.tools.fhirtoomopexamples.synthea;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.fhir.synthea.patient.GetASingleSyntheaPatient;

import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GatASingleSyntheaPatientExampleIntegrationTest {

	@Test
	public void shouldGetJsonString() {
		log.info("Starting test...");
		String jsonString = GetASingleSyntheaPatient.getAsJson();
		jsonString = JsonUtil.prettyPrint(jsonString);
		log.info("Got patient: \n\n" + jsonString + "\n\n");
		log.info("Done.");
	}
	
}
