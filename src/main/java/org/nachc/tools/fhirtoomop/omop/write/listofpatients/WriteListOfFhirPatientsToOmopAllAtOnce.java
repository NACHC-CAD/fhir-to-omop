package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteFhirPatientToOmopRunnable;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Writes a list of fhir patients to omop database. Each patient gets its own
 * thread. All thread logic (e.g. joining back together) is contained within.
 *
 */
@Slf4j
public class WriteListOfFhirPatientsToOmopAllAtOnce {

	private List<Thread> threadList = new ArrayList<Thread>();

	public void exec(List<String> dirList, Connection conn) {
		ArrayList<Connection> connList = new ArrayList<Connection>();
		connList.add(conn);
		exec(dirList, connList);
	}

	public void exec(List<String> dirList, List<Connection> connList) {
		log.info("USING " + connList.size() + " CONNECTIONS.");
		// create the threads
		log.info("Creating threads...");
		int cnt = 0;
		for (String dir : dirList) {
			cnt++;
			if (cnt % 100 == 0) {
				log.info("Creating thread " + cnt + " of " + dirList.size());
			}
			int connToUse = cnt % connList.size();
			Connection conn = connList.get(connToUse);
			WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(dir, conn, cnt);
			Thread thread = new Thread(runnable);
			threadList.add(thread);
		}
		// start the threads
		log.info("Starting threads...");
		log.warn("THE APPLICATION HANGS HERE FOR SEVERAL MINUTES (You'll see some other output from threads that are finishing up below before it actually hangs)");
		log.warn("\n\n\nBE PATIENT...\n\n\n");
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
		for (Connection conn : connList) {
			Database.commit(conn);
		}
		log.info("Done running threads!");
	}

}
