package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFilesFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseWorkerIntegrationTest {

	private static final String DIR = "/test/fhir/test-sets/test-set-02";
	
	private int NUM_OF_WORKERS = 4;

	private int NUM_PATIENTS = 2;
	
	private int NUM_THREADS = 2;

	private int NUM_OF_CONNS = 2;
	
	@Test
	public void shouldGetPatients() {
		log.info("Starting tests...");
		List<String> resources = FileUtil.listResources(DIR, getClass());
		List<Connection> conns = getConnections();
		WriteOmopPeopleToDatabase writer = new WriteOmopPeopleToDatabase(resources, conns, NUM_OF_WORKERS, NUM_PATIENTS, NUM_THREADS);
		writer.exec();
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
