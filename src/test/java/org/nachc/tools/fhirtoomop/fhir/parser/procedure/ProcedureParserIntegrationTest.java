package org.nachc.tools.fhirtoomop.fhir.parser.procedure;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsFiles;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcedureParserIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldParseObservtion() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
		FhirPatient patient = new FhirPatientFactory(resources).buildFromFileList();
		List<ProcedureParser> procList = patient.getProcedureList();
		log.info("Got " + procList.size() + " procedures.");
		assertTrue(procList.size() == 155);
		log.info("Done.");
	}

}
