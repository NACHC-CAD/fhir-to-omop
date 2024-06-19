package org.nachc.tools.fhirtoomop.util.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

public class BootstrapConnectionFactory {

	public static Connection getBootstrapConnection() {
		try {
			String url = AppParams.getBootstrapUrl();
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

}
