package org.nachc.tools.fhirtoomop.util.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDatabaseConnectionFactory {

	private static ArrayList<Connection> conns = new ArrayList<Connection>();

	public static Connection getBootstrapConnection() {
		try {
			String url = AppParams.getBootstrapUrl();
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}

	}

	public static Connection getOmopConnection() {
		return connect(AppParams.getFullyQualifiedDbName());
	}

	private static Connection connect(String schema) {
		try {
			if (schema.trim().endsWith(".dbo")) {
				schema = schema.substring(0, schema.indexOf(".dbo"));
			}
			// get the connection
			log.info("Using schema name: " + schema);
			String url = AppParams.getUrl();
			String uid = AppParams.getUid();
			String pwd = AppParams.getPwd();
			url = url + ";databaseName=" + schema;
			url = url + ";encrypt=false";
			log.info("URL: " + url);
			log.info("UID: " + uid);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			// set auto commit
			conn.setAutoCommit(false);
			// set the schema
			String sqlString = "use " + schema;
			log.info("Setting database:\n" + sqlString);
			Database.update(sqlString, conn);
			// track the open connections
			conns.add(conn);
			log.info("OPEN CONNECTIONS: " + conns.size());
			// done
			return conn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private static Connection connectToMySql(String schema) {
		try {
			String url = AppParams.getUrl();
			String uid = AppParams.getUid();
			String pwd = AppParams.getPwd();
			url = url + "/" + schema;
			url = url + "?rewriteBatchedStatements=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			log.info("URL: " + url);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			conn.setAutoCommit(true);
			conns.add(conn);
			log.info("OPEN CONNECTIONS: " + conns.size());
			return conn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public static void close(Connection conn) {
		Database.close(conn);
		conns.remove(conn);
		log.info("OPEN CONNECTIONS: " + conns.size());
	}

	public static int getConnectionCount() {
		return conns.size();
	}

}
