package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.mssql.MsSqlDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadTestDataSetIntegrationTest {

	@Test
	public void shouldUploadTestData() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			conn = MsSqlDatabaseConnectionFactory.getCdmConnection();
			log.info("Got connection: " + conn);
			File unzipDir = FileUtil.mkdirs(new File("./delete_me"));
			UploadTestDataSet.exec(conn, unzipDir);
		} finally {
			log.info("Closing connection.");
			Database.close(conn);
			log.info("Connection closed.");
		}
		log.info("Done.");
	}
	
}
