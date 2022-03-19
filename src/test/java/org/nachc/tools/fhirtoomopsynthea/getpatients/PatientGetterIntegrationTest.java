package org.nachc.tools.fhirtoomopsynthea.getpatients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.synthea.allpatients.PatientGetter;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientGetterIntegrationTest {

	private static final String TEST_FILE_NAME = "/synthea/patientids/synthea-patient-ids-1107.txt";

	private static final String OUT_FILE_DIR = "/patientids/out";

	private static final int PATIENTS_PER_THREAD = 1;

	private List<PatientGetter> getters = new ArrayList<PatientGetter>();

	private List<Thread> threads = new ArrayList<Thread>();

	@Test
	public void shouldGetPatients() {
		log.info("Staring test...");
		// delete the existing output file
		File outDir = AppParams.getTestOutFile(OUT_FILE_DIR);
		log.info("Deleting existing out file: " + FileUtil.getCanonicalPath(outDir));
		FileUtil.rmdir(outDir);
		FileUtil.mkdirs(outDir);
		// get the test file
		File file = FileUtil.getFile(TEST_FILE_NAME);
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
			if (patientsForThread.size() == PATIENTS_PER_THREAD) {
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
		// done
		log.info("Done.");
	}

}
