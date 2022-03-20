package org.nachc.tools.synthea.allpatients;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patienteverything.SyntheaPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientGetter implements Runnable {

	private List<String> patientIds;

	private String token;
	
	private File outputDir;

	private int threadId;

	public PatientGetter(List<String> patientIds, String token, File outputDir, int threadId) {
		this.patientIds = patientIds;
		this.token = token;
		this.outputDir = outputDir;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		int cnt = 0;
		log.info("Getting token...");
		for (String patientId : patientIds) {
			log.info("Thread " + this.threadId + " getting patient...");
			cnt++;
			new WriteFhirPatientToFile().exec(patientId, token, outputDir);
			log.info("THREAD " + threadId + ": wrote " + cnt + " of " + patientIds.size() + " patients to file (" + patientId + "). " + outputDir);
		}
	}

}
