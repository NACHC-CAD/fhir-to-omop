package org.nachc.tools.fhirtoomop.unittestmanualtest.writesingledir;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.unittestmanualtest.truncate.TruncateAllDataTablesManualTest;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.dir.WriteAllPatientsInDirToOmopUtil;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllPatientsToDatabaseForSingleDirectory {

	private static final String DIR_NAME = "/synthea/patients/synthea-test-patients/test-set-01";

	private static List<Thread> threadList = new ArrayList<Thread>();

	public static void main(String[] args) {
		log.info("Getting connection...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Getting file...");
			File file = FileUtil.getFile(DIR_NAME);
			log.info(FileUtil.getCanonicalPath(file));
			log.info("Truncating tables...");
			TruncateAllDataTablesManualTest.main(null);
			log.info("Writing patients...");
			Timer timer = new Timer();
			timer.start();
			new WriteAllPatientsInDirToOmopUtil().exec(file, conn);
			timer.stop();
			log.info("Start:   " + timer.getStartAsString());
			log.info("Stop:    " + timer.getStopAsString());
			log.info("Elapsed: " + timer.getElapsedString());
		} finally {
			log.info("closing database...");
			Database.close(conn);
		}
		log.info("Done.");
	}

}
