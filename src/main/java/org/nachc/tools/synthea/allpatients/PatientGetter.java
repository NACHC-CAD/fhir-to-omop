package org.nachc.tools.synthea.allpatients;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientGetter implements Runnable {

	private List<String> patientIds;

	private String token;

	private int threadId;

	private File outputDir;

	public PatientGetter(List<String> patientIds, File outputDir, int threadId) {
		this.patientIds = patientIds;
		this.threadId = threadId;
		this.outputDir = outputDir;
	}

	@Override
	public void run() {
		int cnt = 0;
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		for (String patientId : patientIds) {
			log.info("Thread " + this.threadId + " getting patient...");
			cnt++;
			SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
			String json = synthea.fetchEverything(patientId, token);
			String guid = GuidFactory.getGuid();
			String fileName = patientId + "_" + guid + ".json";
			File file = new File(outputDir, fileName);
			FileUtil.write(json, file);
			log.info("THREAD " + threadId + ": wrote " + cnt + " of " + patientIds.size() + " patients to file (" + patientId + "). " + FileUtil.getCanonicalPath(file));
		}
	}

}
