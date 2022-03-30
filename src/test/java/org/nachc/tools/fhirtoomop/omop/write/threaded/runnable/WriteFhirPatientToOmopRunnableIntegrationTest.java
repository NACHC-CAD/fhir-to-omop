package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopRunnableIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldWritePatientUsingRunnable() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFhirPatient();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			int before = Database.count("person", conn);
			log.info("before: " + before);
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			WriteFhirPatientToOmopRunnable runnable = new WriteFhirPatientToOmopRunnable(DIR_PATH, conn, 1);
			Thread thread = new Thread(runnable);
			log.info("Starting thread...");
			thread.start();
			try {
				thread.join();
			} catch (Exception exp) {
				throw new RuntimeException(exp);
			}
			log.info("joined");
			Database.commit(conn);
			int after = Database.count("person", conn);
			log.info("before: " + before);
			log.info("after:  " + after);
			assertTrue(after > before);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
