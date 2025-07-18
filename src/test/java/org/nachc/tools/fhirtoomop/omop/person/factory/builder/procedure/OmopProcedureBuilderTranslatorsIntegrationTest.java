package org.nachc.tools.fhirtoomop.omop.person.factory.builder.procedure;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteOmopPersonToDatabase;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopProcedureBuilderTranslatorsIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/use-cases/measurement-as-proc/0a2a950e-59b0-4669-8007-a505a3f14cbc";

	public static void main(String[] args) {
		TruncateAllDataTables.exec();
		new OmopProcedureBuilderTranslatorsIntegrationTest().shouldWritePatientToDatabase();
	}
	
	/**
	 * If running this test manually, run this prior to running the test (or just use the main above):
	 * TruncateAllDataTables.exec();
	 */
	@Test
	public void shouldWritePatientToDatabase() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
		FhirPatient fhirPatient = new FhirPatientFactory(resources).build();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		log.info("Got connection");
		try {
			int before = Database.count("person", conn);
			log.info("before: " + before);
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			log.info("Got " + omopPerson.getProcedureOccurrenceList().size() + " procedures");
			log.info("Got " + omopPerson.getObservationList().size() + " observations");
			log.info("Got " + omopPerson.getMeasurementList().size() + " measurements");
			log.info("Got " + omopPerson.getConditionOccurrenceList().size() + " conditions");
			// TODO: (JEG) NEED TO FIGURE OUT WHAT'S UP WITH THIS
			assertTrue(omopPerson.getProcedureOccurrenceList().size() == 6 || omopPerson.getProcedureOccurrenceList().size() == 91);
			assertTrue(omopPerson.getObservationList().size() == 13 || omopPerson.getObservationList().size() == 6);
			assertTrue(omopPerson.getMeasurementList().size() == 182 || omopPerson.getMeasurementList().size() == 116);
			assertTrue(omopPerson.getConditionOccurrenceList().size() == 9 || omopPerson.getConditionOccurrenceList().size() == 10);
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
