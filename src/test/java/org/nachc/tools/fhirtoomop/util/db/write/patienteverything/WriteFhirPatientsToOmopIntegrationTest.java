package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientsToOmopIntegrationTest {

	private static final String DIR = "/synthea/patients/synthea-test-patients/test-set-04";

	@Test
	public void shouldWritePatientToDatabase() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		Timer timer = new Timer();
		try {
			log.info("Writing to database...");
			List<String> files = FileUtil.listResources(DIR, getClass());
			timer.start();
			new WriteAllFilesToOmop().exec(files, conn);
			timer.stop();
			log.info("Doing commit...");
			Database.commit(conn);
		} finally {
			log.info("Closing connection...");
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("---");
		log.info("start:   " + timer.getStart());
		log.info("stop:    " + timer.getStop());
		log.info("elapsed: " + timer.getElapsedString());
		log.info("---");
		log.info("Done.");
	}

}
