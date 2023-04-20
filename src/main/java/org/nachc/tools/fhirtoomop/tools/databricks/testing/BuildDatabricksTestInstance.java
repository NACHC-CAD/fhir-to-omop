package org.nachc.tools.fhirtoomop.tools.databricks.testing;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.DatabricksUtil;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildDatabricksTestInstance {

	private static final String SCHEMA_NAME = "demo_cdm";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Building Databricks instance...");
			exec(SCHEMA_NAME, conn);
			log.info("Done building instance.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, Connection conn) {
		DatabricksUtil.uploadTestDatasetCsvFiles(conn);
		DatabricksUtil.createDatabricksSchemaFromCdmDdl(schemaName, conn);
		// next step is to run the python script in the databricks notebook
	}

}
