package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.condition.ConditionParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C_ConditionParserIntegrationTest {

	@Test
	public void shouldGetConditions() {
		log.info("Starting test...");
		PatientEverythingParser patientEverythingParser = TestParams.getPatientEverything();
		// get the condition list
		List<ConditionParser> conditionList = patientEverythingParser.getConditionList();
		log.info("Got " + conditionList.size() + " conditions.");
		assertTrue(conditionList.size() == 5);
		// test a condition
		ConditionParser con = conditionList.get(0);
		// condition code
		String conCode = con.getCode();
		log.info(conCode);
		assertTrue(conCode.equals("75498004"));
		// condition system
		String conSystem = con.getSystem();
		log.info(conSystem);
		assertTrue(conSystem.equals("http://snomed.info/sct"));
		// condition display
		String conDisplay = con.getDisplay();
		log.info(conDisplay);
		assertTrue(conDisplay.equals("Acute bacterial sinusitis (disorder)"));
		// done
		log.info("Done.");
	}

}
