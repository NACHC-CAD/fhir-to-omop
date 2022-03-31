package org.nachc.tools.fhirtoomop.fhir.parser.medicationrequest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicationRequestParserIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldParseMedicationRequests() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFromFileList();
		List<MedicationRequestParser> medReqList = fhirPatient.getMedicationRequestList();
		log.info("Got " + medReqList.size() + " MedicationRequest resources");
		assertTrue(medReqList.size() == 8);
		int cnt = 0;
		
		log.info("\tSystem:  " + medReqList.get(0).getMedicationSystem());
		log.info("\tCode:    " + medReqList.get(0).getMedicationCode());
		log.info("\tDisplay: " + medReqList.get(0).getMedicationDisplay());
		assertTrue(medReqList.get(0).getMedicationSystem().equals("http://www.nlm.nih.gov/research/umls/rxnorm"));
		assertTrue(medReqList.get(0).getMedicationCode().equals("310965"));
		assertTrue(medReqList.get(0).getMedicationDisplay().equals("Ibuprofen 200 MG Oral Tablet"));

		for (MedicationRequestParser medReq : medReqList) {
			cnt++;
			// coding
			Coding coding = medReq.getMedication();
			log.info("Got medication coding(" + cnt + " of " + medReqList.size() + "): " + coding);
			log.info("\tSystem:  " + medReq.getMedicationSystem());
			log.info("\tCode:    " + medReq.getMedicationCode());
			log.info("\tDisplay: " + medReq.getMedicationDisplay());
			assertTrue(medReq.getMedicationSystem() != null);
			assertTrue(medReq.getMedicationCode() != null);
			assertTrue(medReq.getMedicationDisplay() != null);
			// status and intent
			log.info("Status: " + medReq.getStatus());
			assertTrue(medReq.getStatus() != null);
			log.info("Intent: " + medReq.getIntent());
			assertTrue(medReq.getIntent() != null);
			// encounter id
			log.info("Encounter ID: " + medReq.getEncounterId());
			// start date
			log.info("Start date: " + medReq.getStartDate());
			assertTrue(medReq.getStartDate() != null);
		}
		log.info("Done.");
	}

}
