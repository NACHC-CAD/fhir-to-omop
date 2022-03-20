package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.report.LogOmopPersonReport;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingFactoryIntegrationTest {

	private static final String TEST_SRC = "/synthea/patients/synthea-test-patients/test-set-04/5acc8bb4-2d14-4461-a560-228d96459cc3";
	
	@Test
	public void shouldMakeAPerson() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			// get the test files to create the person
			log.info("Starting test...");
			List<String> fileList = FileUtil.listResources(TEST_SRC, getClass());
			log.info("Got " + fileList.size() + " files.");
			for(String file : fileList) {
				log.info("\t" + file);
			}
			// create the person
			OmopPersonEverything person = OmopPersonEverythingFactory.makePerson(fileList, conn);
			// echo the data
			log.info("Got a person: ");
			LogOmopPersonReport.log(person);
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}
	
}
