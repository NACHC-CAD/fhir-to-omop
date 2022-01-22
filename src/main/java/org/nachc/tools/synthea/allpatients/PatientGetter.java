package org.nachc.tools.synthea.allpatients;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientGetter implements Runnable {

	private List<String> patientIds;

	private String token;

	private int threadId;

	private File outputDir;

	public PatientGetter(List<String> patientIds, String token, File outputDir, int threadId) {
		this.patientIds = patientIds;
		this.token = token;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		int cnt = 0;
		for (String patientId : patientIds) {
			cnt++;
//			SyntheaPatientEverythingFetcher synthea = new SyntheaPatientEverythingFetcher();
//			String json = synthea.fetchEverything(patientId, patientId);
//			FileUtil.write(json, this.outputDir);
			log.info("Thread " + this.threadId + " doing work...");
			TimeUtil.sleep(10);
			log.info("THREAD " + threadId + ": wrote " + cnt + " of " + patientIds.size() + " patients to file (" + patientId + ").");
		}
	}

}
