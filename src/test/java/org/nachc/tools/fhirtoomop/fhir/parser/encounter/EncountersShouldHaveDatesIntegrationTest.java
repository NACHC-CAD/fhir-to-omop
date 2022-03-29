package org.nachc.tools.fhirtoomop.fhir.parser.encounter;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.validate.encounter.ValidateEncounter;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncountersShouldHaveDatesIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetDates() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFhirPatient();
		List<EncounterParser> encList = fhirPatient.getEncounterList();
		List<EncounterParser> invalid = new ArrayList<EncounterParser>();
		List<EncounterParser> valid = new ArrayList<EncounterParser>();
		for (EncounterParser enc : encList) {
			ValidateEncounter val = new ValidateEncounter(enc).validate();
			val.validate();
			if (val.isValid() == false) {
				invalid.add(enc);
			} else {
				valid.add(enc);
			}
		}
		log.info("VALID: ");
		for (EncounterParser enc : valid) {
			log.info(enc.getEncounterId());
		}
		if (invalid.size() > 0) {
			for (EncounterParser enc : invalid) {
				log.info(enc.getEncounterId());
			}
		}
		assertTrue(invalid.size() == 0);
		log.info("Done.");
	}

}
