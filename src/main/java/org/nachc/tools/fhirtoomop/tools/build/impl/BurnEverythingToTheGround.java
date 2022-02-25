package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGround {

	public static void exec(Connection conn) {
		String databaseName = AppConnectionParams.getSyntheaSchema();
		databaseName = AppConnectionParams.getCatalogPart(databaseName);
		log.warn("DROPPING DATABASE: " + databaseName);
		Database.update("use master", conn);
		Database.update("drop database if exists " + databaseName, conn);
		log.warn("DATABASE DROPPED: " + databaseName);
	}

	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

}
