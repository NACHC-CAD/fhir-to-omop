package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation.translate;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteOmopPersonToDatabase;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopMeasurementFromProcedureBuilderIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/use-cases/measurement-as-proc/0a2a950e-59b0-4669-8007-a505a3f14cbc";

	@Test
	public void shouldWritePatientToDatabase() {
		log.info("Starting test...");
		TruncateAllDataTables.exec();
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
			WriteOmopPersonToDatabase.exec(omopPerson, conn);
			Database.commit(conn);
			int after = Database.count("person", conn);
			log.info("before: " + before);
			log.info("after: " + after);
			assertTrue(after > before);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
