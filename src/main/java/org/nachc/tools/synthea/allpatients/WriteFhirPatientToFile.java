package org.nachc.tools.synthea.allpatients;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingNextFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToFile {

	private int cnt = 0;

	public void exec(String patientId, String token, File outputDir) {
		exec(patientId, token, outputDir, 0);
	}


	public void exec(String patientId, String token, File outputDir, int numberOfAttempts) {
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String json = synthea.fetchEverything(patientId, token);
		int statusCode = synthea.getStatusCode();
		if (statusCode == 401) {
			// TODO: JEG
		}
		String guid = GuidFactory.getGuid();
		String fileName = cnt + "_" + patientId + "_" + guid + ".json";
		File patientDir = new File(outputDir, patientId);
		File file = new File(patientDir, fileName);
		FileUtil.write(json, file);
		log.info("File Created: " + FileUtil.getCanonicalPath(file));
		getNext(json, patientId, token, patientDir, 0);
	}

	private void getNext(String json, String patientId, String token, File outputDir, int numberOfAttempts) {
		cnt++;
		BundleParser parser = new BundleParser(json);
		String nextUrl = parser.getNextUrl();
		if (nextUrl != null) {
			log.info("Getting next: " + nextUrl);
			SyntheaPatientEverythingNextFetcher synthea = new SyntheaPatientEverythingNextFetcher();
			String nextJson = synthea.fetchNext(nextUrl, token);
			int statusCode = synthea.getStatusCode();
			if (statusCode == 401) {
				// TODO: JEG
			}
			String guid = GuidFactory.getGuid();
			String fileName = cnt + "_" + patientId + "_" + guid + ".json";
			File file = new File(outputDir, fileName);
			FileUtil.write(nextJson, file);
			log.info("File Created: " + FileUtil.getCanonicalPath(file));
			getNext(nextJson, patientId, token, outputDir, 0);
		}
	}

}
