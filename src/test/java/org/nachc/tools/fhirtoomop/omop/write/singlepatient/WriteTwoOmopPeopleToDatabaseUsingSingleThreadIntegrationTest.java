package org.nachc.tools.fhirtoomop.omop.write.singlepatient;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteTwoOmopPeopleToDatabaseUsingSingleThreadIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-sets/test-set-02";

	@Test
	public void shouldTwoWritePatientToDatabase() {
		log.info("Starting test...");
		List<String> dirList = FileUtil.listResources(DIR_PATH, getClass());
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			int cnt = 0;
			for (String dir : dirList) {
				cnt++;
				log.info("------------");
				log.info("WRITING PATIENT " + cnt + " OF " + dirList.size() + " TO DATABASE.");
				log.info("------------");
				List<String> fileList = FileUtil.listResources(dir, getClass());
				FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFromFileList();
				OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
				WriteOmopPersonToDatabase.exec(omopPerson, conn);
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
}
