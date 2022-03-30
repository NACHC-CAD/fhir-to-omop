package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteListOfFhirPatientsToOmopIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-sets/test-set-100";

	private static final int MAX_CONNS = 10;

	private static final int MAX_THREADS = 50;

	@Test
	public void shouldWritePatientsToDatabase() {
		log.info("Starting test...");
		List<String> patientList = FileUtil.listResources(DIR_PATH, getClass());
		// get a connection
		log.info("Getting connection...");
		List<Connection> connList = getConnections();
		log.info("Got connection");
		try {
			int before = Database.count("person", connList.get(0));
			log.info("before: " + before);
			Timer timer = new Timer();
			timer.start();
			WriteListOfFhirPatientsToOmop.exec(patientList, connList, MAX_THREADS);
			timer.stop();
			int after = Database.count("person", connList.get(0));
			log.info("before: " + before);
			log.info("after:  " + after);
			log.info("-------------");
			log.info("start:   " + timer.getStartAsString());
			log.info("stop:    " + timer.getStopAsString());
			log.info("elapsed: " + timer.getElapsedString());
			log.info("-------------");
			assertTrue(after > before);
		} finally {
			for(Connection conn : connList) {
				OmopDatabaseConnectionFactory.close(conn);
			}
		}
		log.info("Done.");
	}

	private List<Connection> getConnections() {
		List<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < MAX_CONNS; i++) {
			rtn.add(OmopDatabaseConnectionFactory.getOmopConnection());
		}
		return rtn;
	}
}
