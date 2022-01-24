package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservationParserIntegrationTest {

	@Test
	public void shouldParseObservtion() {
		PatientEverythingParser patient = AppParams.getPatientEverything();
		List<String> types = patient.getResourceTypes();
		log.info("Got " + types.size() + " types");
		for (String type : types) {
			log.info("\t" + type);
		}
		List<ObservationParser> obsList = patient.getObservationList();
		log.info("Got " + obsList.size() + " observations.");
		assertTrue(obsList.size() == 45);
		// observation id
		for (ObservationParser obs : obsList) {
			String id = obs.getId();
			// id
			log.info("\tObservation ID: " + id);
			assertTrue(id != null && id.length() > 5);
		}
		// encounter id
		log.info("ENCOUNTER IDS:");
		for (ObservationParser obs : obsList) {
			String encounterId = obs.getEncounterId();
			log.info("\tEncounter ID: " + encounterId);
		}
		// observation category
		log.info("CATEGORIES:");
		for (ObservationParser obs : obsList) {
			// category
			Coding catCoding = obs.getCategory();
			String catDisplay = catCoding.getDisplay();
			String catSystem = catCoding.getSystem();
			String catCode = catCoding.getCode();
			log.info("\tCATEGORY: " + StringUtils.rightPad(catCode, 12) + "\t" + catSystem + "\t" + catDisplay);
		}
		log.info("Got " + obsList.size() + " observations.");
		log.info("Done.");
	}

}
