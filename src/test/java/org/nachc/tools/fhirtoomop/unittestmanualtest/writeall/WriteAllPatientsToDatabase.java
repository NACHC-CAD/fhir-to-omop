package org.nachc.tools.fhirtoomop.unittestmanualtest.writeall;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.write.thread.WriteFhirPatientToOmopRunnable;
import org.nachc.tools.fhirtoomop.unittestmanualtest.truncate.TruncateAllTablesManualTest;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllPatientsToDatabase {

	private static final String DIR_NAME = "/synthea/patients/synthea-test-patients/test-set-01";
	
	private static List<Thread> threadList = new ArrayList<Thread>();
	
	public static void main(String[] args) {
		// TODO: (JEG) MOVE THIS BY CREATING A CreateSyntheaOmop CLASS
		log.info("Truncating tables...");
		TruncateAllTablesManualTest.main(null);
		log.info("Starting inserts...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Starting write patients...");
			File dir = FileUtil.getFile(DIR_NAME);
			File[] files = dir.listFiles();
			log.info("Creating threads...");
			// create the threads
			for(File file : files) {
				WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(file, conn);
				Thread thread = new Thread(runnable);
				threadList.add(thread);
			}
			// start the threads
			log.info("Starting threads...");
			for(Thread thread : threadList) {
				thread.start();
			}
			log.info("Joining threads...");
			for(Thread thread : threadList) {
				try {
					thread.join();
				} catch (Exception exp) {
					log.error("* * *EXCEPTION THROWN JOINING THREAD * * *");
				}
			}			
			log.info("Doing commit...");
			Database.commit(conn);
			log.info("Done running threads!");
		} finally {
			log.info("closing database...");
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}
