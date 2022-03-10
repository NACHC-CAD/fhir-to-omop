package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

public class CreateDatabase {

	public static void exec(Connection conn) {
		String databaseName = AppParams.getDbName();
		Database.update("create database " + databaseName, conn);
		Database.update("create database " + databaseName + "_dqd_results", conn);
	}
	
}
