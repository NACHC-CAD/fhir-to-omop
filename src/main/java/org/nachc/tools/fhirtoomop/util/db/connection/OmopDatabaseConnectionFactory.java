package org.nachc.tools.fhirtoomop.util.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.nachc.tools.fhirtoomop.util.db.connection.mssql.MsSqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.type.ConnectionDbmsType;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDatabaseConnectionFactory {

	private static ArrayList<Connection> conns = new ArrayList<Connection>();

	public static Connection getBootstrapConnection() {
		ConnectionDbmsType type = AppParams.getDbmsType();
		Connection conn = null;
		if(type == ConnectionDbmsType.MSSQL) {
			conn = MsSqlDatabaseConnectionFactory.getBootstrapConnection();
		} else if(type == ConnectionDbmsType.POSTGRESQL) {
			conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		} else {
			return null;
		}
		return conn;
	}

	public static Connection getCdmConnection() {
		ConnectionDbmsType type = AppParams.getDbmsType();
		Connection conn = null;
		if(type == ConnectionDbmsType.MSSQL) {
			conn = MsSqlDatabaseConnectionFactory.getCdmConnection();
		} else if(type == ConnectionDbmsType.POSTGRESQL) {
			conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		} else {
			return null;
		}
		conns.add(conn);
		return conn;
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
