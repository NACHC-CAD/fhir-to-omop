package org.nachc.tools.fhirtoomop.util.fhir.parser.condition;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionParserIntegrationTest {

	@Test
	public void shouldGetConditions() {
		log.info("Starting test...");
		PatientEverythingParser patientEverythingParser = AppParams.getPatientEverything();
		// get the condition list
		List<ConditionParser> conditionList = patientEverythingParser.getConditionList();
		log.info("Got " + conditionList.size() + " conditions.");
		assertTrue(conditionList.size() == 5);
		// test a condition
		ConditionParser con = conditionList.get(0);
		// condition id
		String conditionId = con.getConditionId();
		log.info("conditionId: " + conditionId);
		assertTrue(conditionId.equals("25b86d4b-5d09-47c6-9446-b93b067e63ec"));
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
		// condition start date
		Date startDate = con.getStartDate();
		String startDateString = TimeUtil.format(startDate);
		log.info("startDate: " + startDateString);
		assertTrue(startDateString.equals("2009-01-10"));
		// condition end date
		Date endDate = con.getEndDate();
		String endDateString = TimeUtil.format(endDate);
		log.info("endDate: " + endDateString);
		assertTrue(endDateString.equals("2009-01-31"));
		// asserted date
		Date assertedDate = con.getAssertedDate();
		String assertedDateString = TimeUtil.format(assertedDate);
		log.info("assertedDate: " + assertedDateString);
		assertTrue(assertedDateString.equals("2009-01-10"));
		// done
		log.info("Done.");
	}

}
