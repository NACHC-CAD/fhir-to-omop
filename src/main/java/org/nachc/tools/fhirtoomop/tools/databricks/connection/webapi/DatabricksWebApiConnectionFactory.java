package org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabricksWebApiConnectionFactory {

	public static Connection getConnection() {
		try {
			String url = DatabricksProperties.getWebApiJdbcUrl();
			log.info("Getting connection for:\n" + url);
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
	public static Connection getBootstrapConnection() {
		try {
			String url = DatabricksProperties.getWebApiBootStrapJdbcUrl();
			log.info("Getting connection for:\n" + url);
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}
