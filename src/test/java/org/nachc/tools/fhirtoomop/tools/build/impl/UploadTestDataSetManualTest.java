package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.mssql.MsSqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadTestDataSetManualTest {

//	private static final String FILE_NAME = "/broadsea/exported-test-data/demo_cdm.zip";

	private static final String FILE_NAME = "/eunomia/Synthea27Nj_5.4.zip";

	@Test
	public void shouldUploadTestData() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			conn = MsSqlDatabaseConnectionFactory.getCdmConnection();
			log.info("Got connection: " + conn);
			log.info("Truncating data tables...");
			TruncateAllDataTables.exec();
			log.info("Unzipping data zip...");
			File unzipDir = FileUtil.mkdirs(new File("./delete_me"));
			log.info("Uploading data...");
			InputStream is = FileUtil.getInputStream(FILE_NAME);
			UploadCsvFilesZip.exec(conn, is, unzipDir);
			log.info("Done uploading data.");
		} finally {
			log.info("Closing connection.");
			Database.close(conn);
			log.info("Connection closed.");
		}
		log.info("Done.");
	}
	
}
