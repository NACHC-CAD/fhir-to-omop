package org.nachc.tools.fhirtoomop.tools.download.patient.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.patient.thread.DownloadFhirPatientWorkerRunnable;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatients {

	private static int MAX_ACTIVE = AppParams.getDownloadMaxNumberOfActiveWorkers();

	private static int PATIENTS_PER_WORKER = AppParams.getDownloadNumberOfPatientsPerWorker();

	private static List<String> waiting = new ArrayList<String>();

	private static List<DownloadPatientsWorker> active = new ArrayList<DownloadPatientsWorker>();

	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	public static void getPatients(List<String> patientIdList, File outDir) {
		waiting = patientIdList;
		int threadId = 0;
		while (waiting.size() > 0) {
			while (active.size() <= MAX_ACTIVE && waiting.size() > 0) {
				List<String> patientsForWorker = new ArrayList<String>();
				for (int i = 0; i < PATIENTS_PER_WORKER; i++) {
					String patientId = waiting.remove(0);
					patientsForWorker.add(patientId);
					if (waiting.size() == 0) {
						break;
					}
				}
				threadId++;
				DownloadPatientsWorker worker = new DownloadPatientsWorker(patientIdList, outDir);
				DownloadFhirPatientWorkerRunnable runnable = new DownloadFhirPatientWorkerRunnable(worker, threadId);
				Thread thread = new Thread(runnable);
				threads.add(thread);
				active.add(worker);
				thread.start();
			}
			log.info("----------------------");
			log.info("ACTIVE:  " + active.size());
			log.info("WAITING: " + waiting.size());
			TimeUtil.sleep(10);
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception exp) {
				log.info("Could not join thread");
			}
		}
		log.info("Done getting patients for list");
	}

	public static synchronized void done(DownloadPatientsWorker worker) {
		log.info("REMOVING GETTER");
		active.remove(worker);
		log.info("GETTER REMOVED");
	}

}
