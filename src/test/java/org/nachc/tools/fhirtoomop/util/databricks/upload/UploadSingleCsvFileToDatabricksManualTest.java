package org.nachc.tools.fhirtoomop.util.databricks.upload;

import java.io.File;
import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadSingleCsvFileToDatabricksManualTest {

	private static final String SCHEMA_NAME = "demo_cdm";
	
	@Test
	public void shouldUploadFilesToDatabricks() {
		createSchema();
		String databricksFilePath = "/demo_cdm/concept/concept.csv";
		String fileName = "/src/main/resources/databricks/concept/concept.csv";
		File file = FileUtil.getFromProjectRoot(fileName);
		log.info("Doing delete...");
		DeleteCsvFromDatabricks.exec(databricksFilePath);
		log.info("Doing upload...");
		UploadCsvToDatabricks.exec(databricksFilePath, file, SCHEMA_NAME);
		log.info("Done.");
	}
	
	private void createSchema() {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Dropping schema...");
			Database.update("drop database if exists " + SCHEMA_NAME, conn);
			log.info("Creating schema...");
			Database.update("create database " + SCHEMA_NAME, conn);
			log.info("Done creating schema.");
		} finally {
			Database.close(conn);
		}
	}

}
