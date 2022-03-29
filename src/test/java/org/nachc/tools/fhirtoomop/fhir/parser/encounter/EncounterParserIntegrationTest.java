package org.nachc.tools.fhirtoomop.fhir.parser.encounter;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Encounter;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncounterParserIntegrationTest {

	private static final String FILE_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3/0_5acc8bb4-2d14-4461-a560-228d96459cc3_8a37ef8f-098c-438a-a50c-8210f1ce5a4f.json";

	@Test
	public void shouldGetEncounter() {
		log.info("Starting test...");
		String json = FileUtil.getAsString(FILE_PATH);
		BundleParser bundleParser = new BundleParser(json);
		List<Encounter> encounterList = bundleParser.getResourceListForType(Encounter.class);
		// get and test a single encounter
		EncounterParser enc = new EncounterParser(encounterList.get(0));
		// get encounter ids
		String encounterId = enc.getEncounterIdFullyQualified();
		log.info("encounterIdQual: " + encounterId);
		assertTrue(encounterId.equals("Encounter/08bfe31a-6fed-43e8-b2c5-90f5b02b3e77/_history/MTU1NDgxMjczNTM4NzAxMDAwMA"));
		String encounterIdUnc = enc.getEncounterId();
		log.info("encounterIdUnc:  " + encounterIdUnc);
		assertTrue(encounterIdUnc.equals("08bfe31a-6fed-43e8-b2c5-90f5b02b3e77"));
		String encounterIdUncAndQual = enc.getEncounterIdUncAndQual();
		log.info("encounterIdUncAndQual: " + encounterIdUncAndQual);
		assertTrue(encounterIdUncAndQual.equals("08bfe31a-6fed-43e8-b2c5-90f5b02b3e77|Encounter/08bfe31a-6fed-43e8-b2c5-90f5b02b3e77/_history/MTU1NDgxMjczNTM4NzAxMDAwMA"));
		// get encounter date
		Date startDate = enc.getStartDate();
		String startDateString = TimeUtil.format(startDate, "yyyy-MM-dd");
		log.info("startDate: " + startDateString);
		assertTrue("2016-07-07".equals(startDateString));
		Date endDate = enc.getEndDate();
		String endDateString = TimeUtil.format(endDate, "yyyy-MM-dd");
		log.info("endDate: " + endDateString);
		assertTrue("2016-07-07".equals(endDateString));
		// encounter type
		Coding typeCoding = enc.getEncounterType();
		log.info("coding: " + typeCoding);
		log.info("typeCode: " + typeCoding.getCode());
		log.info("typeSys:  " + typeCoding.getSystem());
		log.info("typeDis:  " + typeCoding.getDisplay());
		// done
		log.info("Done.");
	}

}
