package org.nachc.tools.fhirtoomop.fhir.patient.factory;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.validate.ValidateFhirPatient;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirPatientFactoryIntegrationTest {

	private static final String FILE_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetFhirPatient() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(FILE_PATH, getClass());
		FhirPatient pat = new FhirPatientFactory(fileList).buildFhirPatient();
		log.info("Got pat: " + pat.getPatient().getId());
		new ValidateFhirPatient(pat).validate().isValid();
		log.info("Done.");
	}

}
