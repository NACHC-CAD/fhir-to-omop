package org.nachc.tools.fhirtoomop.fhir.parser.condition;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.dstu3.model.Condition;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionParserIntegrationTest {

	private static final String FILE_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3/0_5acc8bb4-2d14-4461-a560-228d96459cc3_8a37ef8f-098c-438a-a50c-8210f1ce5a4f.json";

	@Test
	public void shouldGetConditions() {
		log.info("Starting test...");
		String json = FileUtil.getAsString(FILE_PATH);
		BundleParser bundleParser = new BundleParser(json);
		// get the condition list
		List<Condition> conditionList = bundleParser.getResourceListForType(Condition.class);
		log.info("Got " + conditionList.size() + " conditions.");
		assertTrue(conditionList.size() == 10);
		// test a condition
		ConditionParser con = new ConditionParser(conditionList.get(0));
		// condition id
		String conditionId = con.getConditionId();
		log.info("conditionId: " + conditionId);
		assertTrue(conditionId.equals("25ce9c7c-637f-4553-a7c1-9d04e9fb4acf"));
		// condition code
		String conCode = con.getCode();
		log.info(conCode);
		assertTrue(conCode.equals("72892002"));
		// condition system
		String conSystem = con.getSystem();
		log.info(conSystem);
		assertTrue(conSystem.equals("http://snomed.info/sct"));
		// condition display
		String conDisplay = con.getDisplay();
		log.info(conDisplay);
		assertTrue(conDisplay.equals("Normal pregnancy"));
		// condition start date
		Date startDate = con.getStartDate();
		String startDateString = TimeUtil.format(startDate);
		log.info("startDate: " + startDateString);
		assertTrue(startDateString.equals("2011-03-03"));
		// condition end date
		Date endDate = con.getEndDate();
		String endDateString = TimeUtil.format(endDate);
		log.info("endDate: " + endDateString);
		assertTrue(endDateString.equals("2011-10-06"));
		// asserted date
		Date assertedDate = con.getAssertedDate();
		String assertedDateString = TimeUtil.format(assertedDate);
		log.info("assertedDate: " + assertedDateString);
		assertTrue(assertedDateString.equals("2011-03-03"));
		// done
		log.info("Done.");
	}

}
