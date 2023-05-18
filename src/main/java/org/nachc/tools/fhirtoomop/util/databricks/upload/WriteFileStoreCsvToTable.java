package org.nachc.tools.fhirtoomop.util.databricks.upload;

import java.sql.Connection;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFileStoreCsvToTable {

	public static void exec(String path, String databaseName, String tableName, Connection conn) {
		if(tableExists(databaseName, tableName, conn)) {
			String viewName = createTemporaryView(path, databaseName, tableName, conn);
			doInsert(databaseName, tableName, viewName, conn);
			dropTempView(viewName, conn);
		}
	}
	
	private static String createTemporaryView(String path, String databaseName, String tableName, Connection conn) {
		String viewName = databaseName + "_" + tableName + "_tv";
		String sqlString = "";
		sqlString += "create temporary view " + viewName + " \n";
		sqlString += "using CSV \n";
		sqlString += "options ( \n";
		sqlString += "  path \"" + path + "\", header  \"true\", mode \"FAILFAST\" \n";
		sqlString += ")\n";
		log.info("Sql: \n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done creating temporary view");
		return viewName;
	}
	
	private static void doInsert(String databaseName, String tableName, String viewName, Connection conn) {
		String sqlString = "";
		sqlString += "insert into " + databaseName + "." + tableName + "( \n";
		sqlString += "  select * from " + viewName + "\n";
		sqlString += ")\n";
		Database.update(sqlString, conn);
	}
	
	private static void dropTempView(String viewName, Connection conn) {
		Database.update("drop view " + viewName, conn);
	}

	private static boolean tableExists(String databaseName, String tableName, Connection conn) {
		String sqlString = "select * from " + databaseName + "." + tableName;
		try {
			Database.query(sqlString, conn);
			return true;
		} catch(Exception exp) {
			return false;
		}
	}

}
