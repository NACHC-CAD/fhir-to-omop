package org.nachc.tools.fhirtoomop.tools.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.jar.JarUtil;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.thread.WriteFhirPatientToOmopRunnable;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteTestPatientsToDatabase {

	private static final String DIR_NAME = "/synthea/patients/synthea-test-patients/test-set-01";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		exec(null);
	}

	public static void exec(Integer limit) {
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("--------------------------");
			log.info("Getting file...");
			log.info("Getting files from java path:" + DIR_NAME);
			log.info("Truncating tables...");
			TruncateAllDataTables.exec();
			log.info("Writing patients...");
			Timer timer = new Timer();
			timer.start();
			List<String> files = JarUtil.getResources(DIR_NAME);
			List<Thread> threadList = new ArrayList<Thread>();
			int cnt = 0;
			for (String path : files) {
				cnt++;
				log.info("PATH: " + path);
				String json = FileUtil.getAsString(path);
				WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(path, json, conn, cnt);
				Thread thread = new Thread(runnable);
				threadList.add(thread);
				if(limit != null && cnt >= limit) {
					break;
				}
			}
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
			timer.stop();
			log.info("Start:   " + timer.getStartAsString());
			log.info("Stop:    " + timer.getStopAsString());
			log.info("Elapsed: " + timer.getElapsedString());
		} finally {
			log.info("closing database...");
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
