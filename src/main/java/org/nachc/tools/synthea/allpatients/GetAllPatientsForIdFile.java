package org.nachc.tools.synthea.allpatients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientsForIdFile {
	
	//
	// instance variables
	//

	private File file;
	
	private File outDir;
	
	private int patientsPerThread;

	private List<PatientGetter> getters = new ArrayList<PatientGetter>();

	private List<Thread> threads = new ArrayList<Thread>();

	public GetAllPatientsForIdFile(File file, File outDir, int patientsPerThread) {
		this.file = file;
		this.outDir = outDir;
		this.patientsPerThread = patientsPerThread;
		this.getters = new ArrayList<PatientGetter>();
		this.threads = new ArrayList<Thread>();
	}

	public void getPatients() {
		log.info("Staring test...");
		// get the test file
		List<String> patientIds = FileUtil.getAsList(file);
		log.info("Got " + patientIds.size() + " patientIds (showing first 10)");
		for (int i = 0; i < 10; i++) {
			log.info("\t" + patientIds.get(i));
		}
		// make a new thread for every 10 files
		log.info("Creating threads...");
		int threadId = 0;
		List<String> patientsForThread = new ArrayList<String>();
		for (String patientId : patientIds) {
			patientsForThread.add(patientId);
			if (patientsForThread.size() == patientsPerThread) {
				threadId++;
				PatientGetter getter = new PatientGetter(patientsForThread, outDir, threadId);
				this.getters.add(getter);
				Thread thread = new Thread(getter);
				this.threads.add(thread);
				patientsForThread = new ArrayList<String>();
				log.info("Thread created: " + threadId);
			}
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
		log.info("Done running threads...");
	}

}
