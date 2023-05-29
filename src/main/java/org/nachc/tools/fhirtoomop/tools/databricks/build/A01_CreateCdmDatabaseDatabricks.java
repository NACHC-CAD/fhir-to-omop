package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * The only thing this class does is create the databricks schema to hold the CDM. 
 * If the schema already exists it is dropped.  
 * There is nothing version specific about this class as all it does is create an empty schema.  
 *
 */

@Slf4j
public class A01_CreateCdmDatabaseDatabricks {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String schemaName = DatabricksProperties.getSchemaName();
			exec(schemaName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, Connection conn) {
		// check the connection 
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("Dropping and recreating schema: " + schemaName);
		log.info("-------------------------------");
		// drop the schema if it exists
		log.info("Doing drop...");
		String dropString = "drop database if exists " + schemaName + " cascade";
		log.info(dropString);
		DatabricksDatabase.update(dropString, conn);
		// create the schema
		log.info("Doing create...");
		String sqlString = "create database " + schemaName;
		DatabricksDatabase.update(sqlString, conn);
		// echo status
		log.info("-------------------------------");
		log.info("Done creating schema: " + schemaName);
		log.info("-------------------------------");
	}
	
}
