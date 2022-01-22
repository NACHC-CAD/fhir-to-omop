package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaPatientSummaryListFetcherWriteToFileIntegrationTest {

	private static final int CNT = 5;
	
	@Test
	public void shouldWriteFile() {
		log.info("Starting test...");
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(CNT);
		List<PatientSummaryParser> patientList = synthea.getPatients();
		log.info("Got " + patientList.size() + " patients");
		File file = TestParams.getTestOutFile("patient-list.json");
		String json = synthea.getJson();
		json = JsonUtil.prettyPrint(json);
		log.info("Got json: \n" + json);
		log.info("Writing to file: " + FileUtil.getCanonicalPath(file));
		FileUtil.write(json, file);
		log.info("Done.");
	}
	
}
