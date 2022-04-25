package org.nachc.tools.fhirtoomop.tools.download.patient.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.authenticate.FhirServerAuthenticator;
import org.nachc.tools.fhirtoomop.tools.download.patient.thread.DownloadFhirPatientWorkerRunnable;
import org.nachc.tools.fhirtoomop.tools.download.patient.thread.DownloadPatientRunnable;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Takes a list of patients and uses DownloadFhirPatientToFileRunnable threads
 * to download patients to files.
 * 
 * A new thread is created for each set of patients.
 * 
 * The number of patients per thread is defined in app.properties as
 * downloadNumberOfPatientsPerThread.
 * 
 */
@Slf4j
public class DownloadPatientsWorker {

	private List<String> patientIdList;

	private File outDir;

	private int patientsPerThread;

	private String token;
	
	private List<DownloadPatientRunnable> downloaders = new ArrayList<DownloadPatientRunnable>();

	private List<Thread> threads = new ArrayList<Thread>();

	public DownloadPatientsWorker(List<String> patientIdList, File outDir) {
		this.patientIdList = patientIdList;
		this.outDir = outDir;
		this.downloaders = new ArrayList<DownloadPatientRunnable>();
		this.threads = new ArrayList<Thread>();
		this.patientsPerThread = AppParams.getDownloadNumberOfPatientsPerThread();
	}

	public void getPatients() {
		// get a new token
		FhirServerAuthenticator.refresh();
		// echo status
		log.info("Getting " + this.patientsPerThread + " patients per thread");
		log.info("Got " + patientIdList.size() + " patientIds (showing first 10)");
		for (int i = 0; i < 10 || i >= patientIdList.size() - 1; i++) {
			if(i >= patientIdList.size() - 1) {
				break;
			}
			log.info("\t" + patientIdList.get(i));
		}
		// make a new thread for each set patients
		log.info("Creating threads...");
		int threadId = 0;
		List<String> patientsForThread = new ArrayList<String>();
		for (String patientId : patientIdList) {
			patientsForThread.add(patientId);
			if (patientsForThread.size() == patientsPerThread) {
				threadId++;
				addGetter(threadId, patientsForThread);
				patientsForThread = new ArrayList<String>();
				log.info("Thread created: " + threadId);
			}
		}
		if(patientsForThread.size() > 0) {
			addGetter(threadId, patientsForThread);
		}
		// start threads
		log.info("Starting threads...");
		for (Thread thread : this.threads) {
			thread.start();
		}
		log.info("Running all threads...");
		for (Thread thread : this.threads) {
			try {
				thread.join();
			} catch (Exception exp) {
				log.error("* * *EXCEPTION THROWN JOINING THREAD * * *");
			}
		}
		FhirPatientDownloader.done(this);
		log.info("Done running threads...");
	}

	private void addGetter(int threadId, List<String> patientsForThread) {
		DownloadPatientRunnable runnable = new DownloadPatientRunnable(patientsForThread, token, outDir, threadId);
		this.downloaders.add(runnable);
		Thread thread = new Thread(runnable);
		this.threads.add(thread);
	}

}
