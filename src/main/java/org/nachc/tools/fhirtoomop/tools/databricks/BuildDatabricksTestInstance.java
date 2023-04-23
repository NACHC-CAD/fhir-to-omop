package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.DatabricksUtil;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildDatabricksTestInstance {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Building Databricks instance...");
			exec(conn);
			log.info("Done building instance.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		DatabricksUtil.createDatabricksCsvDatabaseSchema(conn);
		DatabricksUtil.uploadTestDatasetCsvFiles();
		DatabricksUtil.createDatabricksSchemaFromCdmDdl(conn);
		// next step is to run the python script in the databricks notebook
	}

}
