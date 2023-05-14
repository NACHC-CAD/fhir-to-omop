package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

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
		log.info("Got scheama name: " + schemaName);
		log.info("Doing drop...");
		String dropString = "drop database if exists " + schemaName + " cascade";
		log.info(dropString);
		DatabricksDatabase.update(dropString, conn);
		log.info("Doing create...");
		String sqlString = "create database " + schemaName;
		DatabricksDatabase.update(sqlString, conn);
		log.info("Done creating schema.");
	}
	
}
