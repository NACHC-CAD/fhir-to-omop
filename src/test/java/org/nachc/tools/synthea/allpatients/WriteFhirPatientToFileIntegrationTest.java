package org.nachc.tools.synthea.allpatients;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToFileIntegrationTest {

	private static final String patientId = "5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetPatient() {
		log.info("Starting test..");
		String token = SyntheaOauth.fetchToken();
		File outputDir = AppParams.getTestOutFile("single-patient");
		FileUtil.rmdir(outputDir);
		FileUtil.mkdirs(outputDir);
		new WriteFhirPatientToFile().exec(patientId, token, outputDir);
		log.info("Done.");
	}

}
