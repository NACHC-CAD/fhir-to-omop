package org.nachc.tools.synthea.allpatients;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingNextFetcher;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToFile {

	public void exec(String patientId, String token, File outputDir) {
		SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
		String json = synthea.fetchEverything(patientId, token);
		String guid = GuidFactory.getGuid();
		String fileName = patientId + "_" + guid + ".json";
		File file = new File(outputDir, fileName);
		FileUtil.write(json, file);
		log.info("File Created: " + FileUtil.getCanonicalPath(file));
		getNext(json, patientId, token, outputDir);
	}

	private void getNext(String json, String patientId, String token, File outputDir) {
		BundleParser parser = new BundleParser(json);
		String nextUrl = parser.getNextUrl();
		if(nextUrl != null) {
			log.info("Getting next: " + nextUrl);
			SyntheaPatientEverythingNextFetcher synthea = new SyntheaPatientEverythingNextFetcher();
			String nextJson = synthea.fetchNext(nextUrl, token);
			String guid = GuidFactory.getGuid();
			String fileName = patientId + "_" + guid + ".json";
			File file = new File(outputDir, fileName);
			FileUtil.write(nextJson, file);
			log.info("File Created: " + FileUtil.getCanonicalPath(file));
			getNext(nextJson, patientId, token, outputDir);
		}
	}
	
}
