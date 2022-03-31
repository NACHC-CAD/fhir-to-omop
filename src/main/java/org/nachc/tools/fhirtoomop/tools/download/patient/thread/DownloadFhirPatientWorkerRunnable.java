package org.nachc.tools.fhirtoomop.tools.download.patient.thread;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.patient.download.DownloadPatientsWorker;
import org.nachc.tools.fhirtoomop.tools.download.patient.writer.WriteFhirPatientToFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadFhirPatientWorkerRunnable implements Runnable {

	private DownloadPatientsWorker worker;
	
	public DownloadFhirPatientWorkerRunnable(DownloadPatientsWorker worker, int threadId) {
		this.worker = worker;
	}

	@Override
	public void run() {
		worker.getPatients();
	}

}
