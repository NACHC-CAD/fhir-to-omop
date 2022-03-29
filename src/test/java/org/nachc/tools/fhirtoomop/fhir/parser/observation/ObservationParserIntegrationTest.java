package org.nachc.tools.fhirtoomop.fhir.parser.observation;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservationParserIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldParseObservtion() {
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient patient = new FhirPatientFactory(fileList).buildFhirPatient();
		List<String> types = patient.getResourceTypes();
		log.info("Got " + types.size() + " types");
		for (String type : types) {
			log.info("\t" + type);
		}
		List<ObservationParser> obsList = patient.getObservationList();
		log.info("Got " + obsList.size() + " observations.");
		assertTrue(obsList.size() == 52);
		// observation id
		for (ObservationParser obs : obsList) {
			String id = obs.getId();
			// id
			log.info("\tObservation ID: " + id);
			assertTrue(id != null && id.length() > 5);
		}
		log.info("Got " + obsList.size() + " observations.");
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
		// labs
		List<ObservationParser> labs = patient.getLabList();
		log.info("Got " + labs.size() + " labs");
		assertTrue(labs.size() == 33);
		for (ObservationParser obs : labs) {
			log.info("\t" + obs.getCategoryCode() + "\t" + obs.getCategorySystem() + "\t" + obs.getCategoryDisplay());
		}
		log.info("Got " + labs.size() + " labs");
		// surveys
		List<ObservationParser> surveys = patient.getSurveyList();
		log.info("Got " + surveys.size() + " surveys");
		assertTrue(surveys.size() == 3);
		for (ObservationParser obs : surveys) {
			log.info("\t" + obs.getCategoryCode() + "\t" + obs.getCategorySystem() + "\t" + obs.getCategoryDisplay());
		}
		log.info("Got " + surveys.size() + " surveys");
		// vitals
		List<ObservationParser> vitals = patient.getVitalsList();
		log.info("Got " + vitals.size() + " vitals");
		assertTrue(vitals.size() == 16);
		for (ObservationParser obs : vitals) {
			log.info("\t" + obs.getCategoryCode() + "\t" + obs.getCategorySystem() + "\t" + obs.getCategoryDisplay());
		}
		log.info("Got " + vitals.size() + " vitals");
		log.info("Done.");
		// test single obs
		log.info("Testing single obs...");
		ObservationParser obs = obsList.get(0);
		log.info("patientId:   " + obs.getPatientId());
		assertTrue("5acc8bb4-2d14-4461-a560-228d96459cc3".equals(obs.getPatientId()));
		log.info("encounterId: " + obs.getEncounterId());
		assertTrue("f564510b-d2f9-463a-aaa0-b7158388fc11".equals(obs.getEncounterId()));
		log.info("startDate:   " + obs.getStartDate());
		assertTrue(obs.getStartDate() != null);
		log.info("Done.");
	}

}
