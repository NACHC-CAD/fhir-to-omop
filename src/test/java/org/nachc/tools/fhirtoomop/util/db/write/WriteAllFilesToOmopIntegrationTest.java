package org.nachc.tools.fhirtoomop.util.db.write;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.TestParams;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllFilesToOmopIntegrationTest {

	private static final int NUMBER_OF_PATIENTS_TO_CREATE = 10;

	@Test
	public void shouldWritePatients() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			// get the current number of records
			log.info("Record count before test: ");
			int before = getNumberOfPersonRecords(conn);
			// get the data files and then cull out the test set
			log.info("Getting files...");
			List<String> allFiles = TestParams.getFhirPatientsDirListing();
			List<String> files = allFiles.subList(0, NUMBER_OF_PATIENTS_TO_CREATE);
			log.info("Got " + allFiles.size() + " files.");
			// write the files to the database
			log.info("Writing " + files.size() + " files.");
			new WriteAllFilesToOmop().exec(files, conn);
			// assert that they got there
			int after = getNumberOfPersonRecords(conn);
			int added = after - before;
			log.info("before: " + before);
			log.info("after   " + after);
			log.info("added   " + added);
			assertTrue(added == NUMBER_OF_PATIENTS_TO_CREATE);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

	private Integer getNumberOfPersonRecords(Connection conn) {
		String sqlString = "select count(*) row_count from person";
		Data data = Database.query(sqlString, conn);
		Row row = data.get(0);
		String rowCountString = row.get("rowCount");
		Integer rtn = Integer.parseInt(rowCountString);
		return rtn;
	}

}
