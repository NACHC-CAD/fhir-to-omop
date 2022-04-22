package org.nachc.tools.fhirtoomop.omop.write.threaded;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseWorkerIntegrationTest {

//	private static final String DIR = "D:\\NACHC\\SYNTHEA\\TEST\\SYNTHEA_MICRO\\synthea-micro-patients";
	
	private static final String DIR = "/test/fhir/test-sets/test-set-10";
	
	private int NUM_OF_CONNS = 100;

	private int MAX_PER_BATCH = 100;
	
	@Test
	public void shouldGetPatients() {
		log.info("Starting test...");
		List<Connection> connectionList = getConnections();
		Timer timer = new Timer();
		int cntBefore;
		int cntAfter;
		try {
			log.info("Getting conns...");
			Connection conn = connectionList.get(0);
			cntBefore = Database.count("person", conn);
			log.info("Getting dirs...");
			List<String> dirs = FileUtil.listResources(DIR, getClass());
			List<FhirPatientResources> fileListList = new ArrayList<FhirPatientResources>();
			int cnt = 0;
			int batchNumber = 0;
			timer.start();
			for(String dir : dirs) {
				cnt++;
				if(cnt >= MAX_PER_BATCH) {
					cnt = 0;
					batchNumber++;
					log.info("WRITING BATCH " + batchNumber);
					new WriteOmopPeopleToDatabaseWorker(fileListList, connectionList).exec();
					fileListList = new ArrayList<FhirPatientResources>();
				}
				List<String> files = FileUtil.listResources(dir, getClass());
				FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(files);
				fileListList.add(resources);
			}
			if(fileListList.size() > 0) {
				new WriteOmopPeopleToDatabaseWorker(fileListList, connectionList).exec();
			}
			timer.stop();
			cntAfter = Database.count("person", conn);
			log.info("Before: " + cntBefore);
			log.info("After:  " + cntAfter);
			log.info("Time elapsed: " + timer.getElapsedString());
			assertTrue(cntAfter > cntBefore);
		} finally {
			closeConnections(connectionList);
		}
		log.info("Before: " + cntBefore);
		log.info("After:  " + cntAfter);
		log.info("Time elapsed: " + timer.getElapsedString());
		log.info("Done.");
	}

	private List<Connection> getConnections() {
		List<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < NUM_OF_CONNS; i++) {
			Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
			rtn.add(conn);
		}	
		return rtn;
	}
	
	private void closeConnections(List<Connection> connList) {
		for(Connection conn : connList) {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}


	
}
