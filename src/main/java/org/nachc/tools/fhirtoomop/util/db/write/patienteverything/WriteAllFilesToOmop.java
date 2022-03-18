package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.thread.WriteFhirPatientToOmopRunnable;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllFilesToOmop {

	private List<Thread> threadList = new ArrayList<Thread>();

	/**
	 * 
	 * Writes all files in the given directory to the omop database.
	 * 
	 */
	public void exec(String dir, Connection conn) {
		List<String> files = FileUtil.listResources(dir, getClass());
		exec(files, conn);
	}

	public void exec(String dir, Integer limit, Connection conn) {
		List<String> files = FileUtil.listResources(dir, getClass());
		if(limit != null && files.size() > limit) {
			files = files.subList(0, limit);
		}
		exec(files, conn);
	}

	public void exec(String[] files, Connection conn) {
		List<String> list = Arrays.asList(files);
		exec(list, conn);
	}

	public void exec(List<String> files, Connection conn) {
		ArrayList<Connection> connList = new ArrayList<Connection>();
		connList.add(conn);
		exec(files, connList);
	}

	/**
	 * 
	 * Writes all the given files to the omop database.
	 * 
	 */
	public void exec(List<String> files, List<Connection> connList) {
		log.info("USING " + connList.size() + " CONNECTIONS.");
		// create the threads
		log.info("Creating threads...");
		int cnt = 0;
		for (String file : files) {
			cnt++;
			if(cnt % 100 == 0) {
				log.info("Creating thread " + cnt + " of " + files.size());
			}
			int connToUse = cnt % connList.size();
			Connection conn = connList.get(connToUse);
			WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(file, conn, cnt);
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
