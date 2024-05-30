package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBR00a_CreateOhdsiDatabaseForWebApiSchema {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = null;
		try {
			// this class uses the bootstrap connection
			conn = DatabricksWebApiConnectionFactory.getBootstrapConnection();
			createIfDoesntExist(conn);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	private static void createIfDoesntExist(Connection conn) {
		String dbName = DatabricksProperties.getWebApiDatabase();
		boolean exists = exists(conn, dbName);
		if(exists == false) {
			log.info("! ! ! creating ohdsi database ! ! !");
			String sqlString = "create database \"" + dbName + "\"";
			log.info("SQL: \n" + sqlString);
			Database.update(sqlString, conn);
			log.info("Done with create.");
		} else {
			log.info(dbName + " DATABASE ALREADY EXISTS, SKIPPING CREATE DATABASE");
		}
	}

	private static boolean exists(Connection conn, String dbName) {
		log.info("Checking to see if PostgreSql database exists...");
		String sqlString = "select datname as datname from pg_database where datname = ?";
		Data data = Database.query(sqlString, dbName, conn);
		if(data.size() == 1) {
			String fromDb = data.get(0).get("datname");
			if(dbName != null && dbName.equals(fromDb)) {
				return true;
			}
		}
		return false;
	}

}
