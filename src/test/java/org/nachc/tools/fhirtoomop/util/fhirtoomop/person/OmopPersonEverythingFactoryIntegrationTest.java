package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingFactoryIntegrationTest {

	private static final String TEST_SRC = "/synthea/patients/synthea-test-patients/test-set-03/00eac556-25e7-4a0d-a5e1-06895ea6f6b3";
	
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
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}
	
}
