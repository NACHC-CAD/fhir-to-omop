package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;
import java.sql.SQLNonTransientConnectionException;

import org.nachc.tools.fhirtoomop.tools.databricks.util.DatabricksUtil;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
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
			String schemaName = DatabricksProperties.getSchemaName();
			exec(schemaName, conn);
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

	public static void exec(String schemaName, Connection conn) {
		DatabricksUtil.createDatabricksCdmSchema(schemaName, conn);
		DatabricksUtil.createDatabricksCdmSchemaObjectsFromCdmDdl(conn);
		DatabricksUtil.uploadTestDatasetCsvFiles();
		// next step is to run the python script in the databricks notebook
	}

}
