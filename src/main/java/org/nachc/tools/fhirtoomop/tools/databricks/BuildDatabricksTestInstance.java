package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;
import java.sql.SQLNonTransientConnectionException;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
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
			try {
				DatabricksDatabase.close(conn);
			} catch(Exception exp) {
				log.info("An exception was thrown trying to close the connection. This seems to happen with Databricks sometimes");
			}
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		DatabricksUtil.createDatabricksSchema(conn);
		DatabricksUtil.createDatabricksSchemaObjectsFromCdmDdl(conn);
		DatabricksUtil.uploadTestDatasetCsvFiles();
		// next step is to run the python script in the databricks notebook
	}

}
