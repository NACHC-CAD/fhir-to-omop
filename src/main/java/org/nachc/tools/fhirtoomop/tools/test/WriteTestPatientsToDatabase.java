package org.nachc.tools.fhirtoomop.tools.test;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
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
			log.info("Getting file...");
			File file = FileUtil.getFile(DIR_NAME);
			log.info(FileUtil.getCanonicalPath(file));
			log.info("Truncating tables...");
			TruncateAllDataTables.exec();
			log.info("Writing patients...");
			Timer timer = new Timer();
			timer.start();
			new WriteAllFilesToOmop().exec(file, limit, conn);
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
