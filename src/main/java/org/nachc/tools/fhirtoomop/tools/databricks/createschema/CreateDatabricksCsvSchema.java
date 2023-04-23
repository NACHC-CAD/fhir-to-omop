package org.nachc.tools.fhirtoomop.tools.databricks.createschema;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabricksCsvSchema {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String schemaName = DatabricksProperties.getSchemaName();
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
