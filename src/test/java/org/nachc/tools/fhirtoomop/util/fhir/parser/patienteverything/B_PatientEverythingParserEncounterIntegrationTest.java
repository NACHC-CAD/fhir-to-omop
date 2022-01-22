package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class B_PatientEverythingParserEncounterIntegrationTest {

	@Test
	public void shouldGetEncounters() {
		log.info("Starting test...");
		// get the test patient $everything and the encounters
		PatientEverythingParser patient = AppParams.getPatientEverything();
		List<EncounterParser> encounterList = patient.getEncounterList();
		int listSize = encounterList.size();
		log.info("Got " + listSize + " encounters");
		assertTrue(listSize == 10);
		// get and test a single encounter
		EncounterParser enc = encounterList.get(0);
		// get encounter ids
		String encounterId = enc.getEncounterId();
		log.info("encounterIdQual: " + encounterId);
		assertTrue(encounterId.equals("Encounter/051b0d30-03d3-4d6d-a070-f8d363ef277f/_history/MTU1NDgxMjczNjQ3Nzk3NjAwMA"));
		String encounterIdUnc = enc.getEncounterIdUnqualified();
		log.info("encounterIdUnc:  " + encounterIdUnc);
		assertTrue(encounterIdUnc.equals("051b0d30-03d3-4d6d-a070-f8d363ef277f"));
		String encounterIdUncAndQual = enc.getEncounterIdUncAndQual();
		log.info("encounterIdUncAndQual: " + encounterIdUncAndQual);
		assertTrue(encounterIdUncAndQual.equals("051b0d30-03d3-4d6d-a070-f8d363ef277f|Encounter/051b0d30-03d3-4d6d-a070-f8d363ef277f/_history/MTU1NDgxMjczNjQ3Nzk3NjAwMA"));
		// get encounter date
		Date startDate = enc.getStartDate();
		String startDateString = TimeUtil.format(startDate, "yyyy-MM-dd");
		log.info("startDate: " + startDateString);
		assertTrue("2009-01-10".equals(startDateString));
		Date endDate = enc.getEndDate();
		String endDateString = TimeUtil.format(endDate, "yyyy-MM-dd");
		log.info("endDate: " + endDateString);
		assertTrue("2009-01-10".equals(endDateString));
		// done
		log.info("Done.");
	}

}
