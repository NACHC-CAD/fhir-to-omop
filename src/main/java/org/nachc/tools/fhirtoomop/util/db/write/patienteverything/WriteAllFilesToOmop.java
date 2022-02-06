package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.thread.WriteFhirPatientToOmopRunnable;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllFilesToOmop {

	private List<Thread> threadList = new ArrayList<Thread>();

	/**
	 * 
	 * Writes all files in the given directory to the omop database.  
	 * 
	 */
	public void exec(File dir, Connection conn) {
		File[] files = dir.listFiles();
		exec(files, conn);
	}

	/**
	 * 
	 * Writes all the given files to the omop database.  
	 * 
	 */
	public void exec(File[] files, Connection conn) {
		// create the threads
		log.info("Creating threads...");
		int cnt = 0;
		for (File file : files) {
			cnt++;
			WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(file, conn, cnt);
			Thread thread = new Thread(runnable);
			threadList.add(thread);
		}
		// start the threads
		log.info("Starting threads...");
		for (Thread thread : threadList) {
			thread.start();
		}
		log.info("Joining threads...");
		for (Thread thread : threadList) {
			try {
				thread.join();
			} catch (Exception exp) {
				log.error("* * *EXCEPTION THROWN JOINING THREAD * * *");
			}
		}
		log.info("Doing commit...");
		Database.commit(conn);
		log.info("Done running threads!");
	}

}
