package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest.MedicationRequestParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientEverythingParserGetMedicationListIntegrationTest {

	private static final String FILE_PATH = "/synthea/patients/usecases/patient-with-medication-request/1_5acc8bb4-2d14-4461-a560-228d96459cc3_3e7d80fc-f23b-4a68-8639-a74945618d50.json";
	
	@Test
	public void shouldGetMedicationList() {
		log.info("Getting patient...");
		String json = FileUtil.getAsString(FILE_PATH);
		PatientEverythingParser parser = new PatientEverythingParser(json);
		List<MedicationRequestParser> list = parser.getMedicationRequestList();
		log.info("Got " + list.size() + " medications");
		log.info("Done.");
	}
	
}
