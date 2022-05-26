package org.nachc.tools.fhirtoomop.util.db.connection.postgres;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresDatabaseConnectionFactory {

	public static Connection getBootstrapConnection() {
		try {
			String url = AppParams.getPostgresBootstrapUrl();
			String uid = AppParams.getPostgresBootstrapUid();
			String pwd = AppParams.getPostgresBootstrapPwd();
			url += "OHDSI";
			log.info("Getting connection for url: \n" + url);
			log.info("uid: " + uid);
			log.info("pwd: " + pwd);
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres?&user=postgres&password=stripedbass");
			return conn;
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}

	}

}
