package org.nachc.cosmos.tools.fhirtoomop.unittestintegrationtest.synthea.basicqueries;

import java.io.File;

import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummarylistbundle.PatientSummaryListBundleParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C_GetEverythingForSinglePatient {

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		log.info("Done.");
		String patientJson = new SyntheaPatientSummaryListFetcher().fetchPatients(1);
		Patient fhirPatient = new PatientSummaryListBundleParser(patientJson).getPatients().get(0);
		PatientSummaryParser patient = new PatientSummaryParser(fhirPatient);
		String patientId = patient.getId();
		log.info("Got patient: " + patientId);
		log.info("Getting everything...");
		SyntheaPatientEverythingFetcher synthia = new SyntheaPatientEverythingFetcher();
		String everythingJson = synthia.fetchEverything(patientId);
		log.info("Status: " + synthia.getStatusCode());
		log.info("Got response: \n" + JsonUtil.prettyPrint(everythingJson) + "\n\n");
		log.info("Status: " + synthia.getStatusCode());
		File file = AppParams.getOutFile("everything-patient.json");
		log.info("Writing file to: " + FileUtil.getCanonicalPath(file));
		FileUtil.write(JsonUtil.prettyPrint(everythingJson), file);
		log.info("Done.");
	}

}
