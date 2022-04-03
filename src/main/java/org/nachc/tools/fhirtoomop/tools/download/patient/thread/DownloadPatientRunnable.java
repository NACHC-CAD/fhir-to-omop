package org.nachc.tools.fhirtoomop.tools.download.patient.thread;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.patient.writer.WriteFhirPatientToFile;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatientRunnable implements Runnable {

	private List<String> patientIds;

	private String token;
	
	private File outputDir;

	private int threadId;

	private int maxRetries;
	
	public DownloadPatientRunnable(List<String> patientIds, String token, File outputDir, int threadId) {
		this.patientIds = patientIds;
		this.token = token;
		this.outputDir = outputDir;
		this.threadId = threadId;
		this.maxRetries = AppParams.getDownloadRetryCount();
	}

	@Override
	public void run() {
		for(String patientId : this.patientIds) {
			write(patientId, 0);
		}
	}
	
	private void write(String patientId, int retryCount) {
		try {
			new WriteFhirPatientToFile().exec(patientId, token, outputDir);
		} catch(Exception exp) {
			if(retryCount >= maxRetries) {
				throw new RuntimeException(exp);
			} else {
				retryCount++;
				log.info("! ! ! DOING RETRY (" + retryCount + " of " + maxRetries + ") ! ! !");
				TimeUtil.sleep(3);
				write(patientId, retryCount);
			}
		}
	}

}
