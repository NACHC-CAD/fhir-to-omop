package org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicationRequestParserIntegrationTest {

	@Test
	public void shouldParseMedicationRequests() {
		log.info("Starting test...");
		PatientEverythingParser patient = TestParams.getPatientEverything();
		List<MedicationRequestParser> medReqList = patient.getMedicationRequestList();
		log.info("Got " + medReqList.size() + " MedicationRequest resources");
		assertTrue(medReqList.size() == 2);
		int cnt = 0;
		for (MedicationRequestParser medReq : medReqList) {
			cnt++;
			// coding
			Coding coding = medReq.getMedication();
			log.info("Got medication coding(" + cnt + " of " + medReqList.size() + "): " + coding);
			log.info("\tSystem:  " + medReq.getMedicationSystem());
			log.info("\tCode:    " + medReq.getMedicationCode());
			log.info("\tDisplay: " + medReq.getMedicationDisplay());
			// status
		}
		log.info("Done.");
	}

}
