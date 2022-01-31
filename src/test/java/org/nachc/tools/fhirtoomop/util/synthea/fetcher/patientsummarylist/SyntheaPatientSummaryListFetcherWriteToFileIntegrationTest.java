package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaPatientSummaryListFetcherWriteToFileIntegrationTest {

	private static final int CNT = 5;

	@Test
	public void shouldWriteFile() {
		log.info("Starting test...");
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(CNT, token);
		List<PatientSummaryParser> patientList = synthea.getPatients();
		log.info("Got " + patientList.size() + " patients");
		File file = AppParams.getTestOutFile("patient-list.json");
		String json = synthea.getJson();
		json = JsonUtil.prettyPrint(json);
		log.info("Got json: \n" + json);
		log.info("Writing to file: " + FileUtil.getCanonicalPath(file));
		FileUtil.write(json, file);
		log.info("Done.");
	}

}