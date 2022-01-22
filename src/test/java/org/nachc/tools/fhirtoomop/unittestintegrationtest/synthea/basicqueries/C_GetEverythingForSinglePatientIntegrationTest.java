package org.nachc.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.unittesttool.synthea.patient.PatientIdFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C_GetEverythingForSinglePatientIntegrationTest {

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		// get the oauth token
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		// get a patient id
		String patientId = PatientIdFetcher.getASinglePatientId(token);
		log.info("Got patient: " + patientId);
		log.info("Getting everything...");
		// get everything for that patient from synthea
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String everythingJson = synthea.fetchEverything(patientId, token);
		log.info("Status: " + synthea.getStatusCode());
		log.info("Got response: \n" + JsonUtil.prettyPrint(everythingJson) + "\n\n");
		log.info("Status: " + synthea.getStatusCode());
		// write the patient to a file in case someone wants to take a closer look
		File file = TestParams.getTestOutFile("everything-patient.json");
		log.info("Writing file to: " + FileUtil.getCanonicalPath(file));
		FileUtil.write(JsonUtil.prettyPrint(everythingJson), file);
		// assert that we got a patient
		log.info("Status code: " + synthea.getStatusCode());
		assertTrue(synthea.getStatusCode() == 200);
		log.info("Done.");
	}

}
