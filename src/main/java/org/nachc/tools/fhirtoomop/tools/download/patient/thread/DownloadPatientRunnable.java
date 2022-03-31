package org.nachc.tools.fhirtoomop.tools.download.patient.thread;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.patient.writer.WriteFhirPatientToFile;

public class DownloadPatientRunnable implements Runnable {

	private List<String> patientIds;

	private String token;
	
	private File outputDir;

	private int threadId;

	public DownloadPatientRunnable(List<String> patientIds, String token, File outputDir, int threadId) {
		this.patientIds = patientIds;
		this.token = token;
		this.outputDir = outputDir;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		for(String patientId : this.patientIds) {
			new WriteFhirPatientToFile().exec(patientId, token, outputDir);
		}
	}

}
