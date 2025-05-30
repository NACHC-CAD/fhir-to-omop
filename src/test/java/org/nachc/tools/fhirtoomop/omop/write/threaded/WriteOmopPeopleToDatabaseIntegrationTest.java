package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFilesFactory;
import org.nachc.tools.fhirtoomop.omop.util.id.fixer.FixSequences;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteTwoOmopPeopleToDatabaseUsingSingleThreadIntegrationTest;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseIntegrationTest {

//	private static final String DIR = "D:\\NACHC\\SYNTHEA\\TEST\\SYNTHEA_MICRO\\synthea-micro-patients";
	
	private static final String DIR = "/test/fhir/test-sets/test-set-100";
	
	private static final int workers = 10;
	
	private static final int patientsPerWorker = 10;
	
	private static final int numberOfThreadsPerWorker = 10;
	
	private static final int numberOfConnections = 10;
	
	public static void main(String[] args) {
		TruncateAllDataTables.exec();
		new WriteOmopPeopleToDatabaseIntegrationTest().shouldWritePeopleToDatabase();
	}
	
	@Test
	public void shouldWritePeopleToDatabase() {
		log.info("Starting test...");
		List<Connection> conns = getConnections();
		Timer timer = new Timer();
		try {
			timer.start();
			List<String> resources = FileUtil.listResources(DIR, getClass());
			WriteOmopPeopleToDatabase writer = new WriteOmopPeopleToDatabase(resources, conns, workers, patientsPerWorker, numberOfThreadsPerWorker);
			writer.exec();
			timer.stop();
		} finally {
			try {
				// FixSequences.exec();
			} finally {
				closeConnections(conns);
			}
		}
		log.info("---");
		log.info("Elapsed time: " + timer.getElapsedString());
		log.info("---");
		log.info("Done.");
	}
	
	private List<Connection> getConnections() {
		List<Connection> rtn = new ArrayList<Connection>();
		for(int i=0;i<numberOfConnections;i++) {
			Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
			rtn.add(conn);
		}
		return rtn;
	}
	
	private void closeConnections(List<Connection> conns) {
		for(Connection conn : conns) {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}
	
}
