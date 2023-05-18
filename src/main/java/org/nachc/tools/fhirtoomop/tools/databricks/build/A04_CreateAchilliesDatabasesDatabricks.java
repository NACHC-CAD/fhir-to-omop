package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A04_CreateAchilliesDatabasesDatabricks {

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

	public static void exec(String databaseName, Connection conn) {
		log.info("Done creating Achilles databases.");
		try {
			createDatabase(databaseName + "_ach_res", conn);
			createDatabase(databaseName + "_ach_tmp", conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating Achilles databases.");
	}

	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		Database.update("drop database if exists " + databaseName + " cascade", conn);
		Database.update("create database " + databaseName, conn);
	}
}
