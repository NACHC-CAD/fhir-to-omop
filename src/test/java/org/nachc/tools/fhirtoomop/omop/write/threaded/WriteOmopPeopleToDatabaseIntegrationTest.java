package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseIntegrationTest {

	private static final String DIR = "D:\\NACHC\\SYNTHEA\\TEST\\SYNTHEA_MICRO\\synthea-micro-patients";
	
//	private static final String DIR = "/test/fhir/test-sets/test-set-100";
	
	private static final int workers = 10;
	
	private static final int patientsPerWorker = 10;
	
	private static final int numberOfConnections = 10;
	
	@Test
	public void shouldWritePeopleToDatabase() {
		log.info("Starting test...");
		List<Connection> conns = getConnections();
		try {
			List<FhirPatientResources> resourceList = getResources();
			WriteOmopPeopleToDatabase.exec(resourceList, conns, workers, patientsPerWorker);
		} finally {
			closeConnections(conns);
		}
		log.info("Done.");
	}
	
	private List<FhirPatientResources> getResources() {
		List<FhirPatientResources> rtn = new ArrayList<FhirPatientResources>();
		List<String> fileList = FileUtil.listResources(DIR, getClass());
		for(String str : fileList) {
			List<String> files = FileUtil.listResources(str, getClass());
			FhirPatientResourcesAsFiles resource = new FhirPatientResourcesAsFiles(files);
			rtn.add(resource);
		}
		return rtn;
	}
	
	private List<Connection> getConnections() {
		List<Connection> rtn = new ArrayList<Connection>();
		for(int i=0;i<numberOfConnections;i++) {
			Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
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
