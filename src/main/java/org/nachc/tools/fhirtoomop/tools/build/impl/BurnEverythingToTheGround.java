package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGround {

	public static void exec(Connection conn) {
		// drop the database
		String databaseName = AppConnectionParams.getSyntheaSchema();
		databaseName = AppConnectionParams.getCatalogPart(databaseName);
		log.warn("DROPPING DATABASE: " + databaseName);
		Database.update("use master", conn);
		Database.update("drop database if exists " + databaseName, conn);
		log.warn("DATABASE DROPPED: " + databaseName);
		// drop the login
		String uid = AppConnectionParams.getUid();
		log.warn("DROPPING LOGIN: " + uid);
		boolean loginExists = loginExists(conn, uid);
		if(loginExists) {
			log.info("Doing drop...");
			Database.update("drop login " + uid, conn);
			log.info("Done with drop.");
		}
		log.warn("LOGIN DROPPED: " + uid);
		log.info("Done.");
	}

	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	private static boolean loginExists(Connection conn, String uid) {
		String sqlString = "select * from sys.server_principals sp where sp.name = ?";
		Data data = Database.query(sqlString, uid, conn);
		if(data.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
