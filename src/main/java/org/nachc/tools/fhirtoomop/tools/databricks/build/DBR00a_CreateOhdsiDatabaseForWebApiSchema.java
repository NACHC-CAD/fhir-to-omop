package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
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
			delete(conn);
			create(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	private static void delete(Connection conn) {
		log.info("! ! ! DELETING OHDSI DATABASE ! ! !");
		String sqlString = "drop database if exists \"" + DatabricksProperties.getWebApiDatabase() + "\"";
		log.info("SQL: \n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done with drop.");
	}

	private static void create(Connection conn) {
		log.info("! ! ! CREATING OHDSI DATABASE ! ! !");
		String sqlString = "create database \"" + DatabricksProperties.getWebApiDatabase() + "\"";
		log.info("SQL: \n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done with create.");
	}

}
