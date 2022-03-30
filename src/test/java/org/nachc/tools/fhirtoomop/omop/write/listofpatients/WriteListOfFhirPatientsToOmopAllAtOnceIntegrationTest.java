package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteListOfFhirPatientsToOmopAllAtOnceIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-sets/test-set-01";

	@Test
	public void shouldWritePatientsToDatabase() {
		log.info("Starting test...");
		List<String> patientList = FileUtil.listResources(DIR_PATH, getClass());
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			int before = Database.count("person", conn);
			log.info("before: " + before);
			Timer timer = new Timer();
			timer.start();
			WriteListOfFhirPatientsToOmopAllAtOnce writer = new WriteListOfFhirPatientsToOmopAllAtOnce();
			writer.exec(patientList, conn);
			timer.stop();
			int after = Database.count("person", conn);
			log.info("before: " + before);
			log.info("after:  " + after);
			log.info("-------------");
			log.info("start:   " + timer.getStartAsString());
			log.info("stop:    " + timer.getStopAsString());
			log.info("elapsed: " + timer.getElapsedString());
			log.info("-------------");
			assertTrue(after > before);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}

	}

}
