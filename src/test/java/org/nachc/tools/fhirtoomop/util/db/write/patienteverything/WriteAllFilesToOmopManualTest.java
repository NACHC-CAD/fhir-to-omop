package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAllFilesToOmopManualTest {

	private static final String DIR = "D:\\NACHC\\SYNTHEA\\_DEV\\test-set-10";

	@Test
	public void shouldWriteFiles() {
		log.info("Starting test..");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			List<String> files = FileUtil.listResources(DIR, getClass());
			new WriteAllFilesToOmop().exec(files, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}
