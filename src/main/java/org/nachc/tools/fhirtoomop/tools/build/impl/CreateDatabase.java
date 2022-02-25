package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Database;

public class CreateDatabase {

	public static void exec(Connection conn) {
		String databaseName = AppConnectionParams.getDbName();
		String sqlString = "create database " + databaseName;
		Database.update(sqlString, conn);
	}
	
}
