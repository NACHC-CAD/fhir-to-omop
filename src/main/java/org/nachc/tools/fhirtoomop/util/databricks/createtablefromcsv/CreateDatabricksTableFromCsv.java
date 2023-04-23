package org.nachc.tools.fhirtoomop.util.databricks.createtablefromcsv;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabricksTableFromCsv {

	public static void exec(String schemaName, String tableName, String pathToFile, Connection conn) {
		dropTable(schemaName, tableName, conn);
		createTable(schemaName, tableName, pathToFile, conn);
	}

	private static void dropTable(String schemaName, String tableName, Connection conn) {
		log.info("Dropping table...");
		String sqlString = "drop table if exists " + schemaName + "." + tableName;
		log.info("sqlString: \n" + sqlString);
		DatabricksDatabase.update(sqlString, conn);
		log.info("Done dropping table.");
	}

	private static void createTable(String schemaName, String tableName, String pathToFile, Connection conn) {
		log.info("Getting sql string...");
		String sqlString = getSqlString(schemaName, tableName, pathToFile);
		log.info("sqlString: \n" + sqlString);
		log.info("Creating table...");
		DatabricksDatabase.update(sqlString, conn);
		log.info("Done creating table.");
	}
	
	private static String getSqlString(String schemaName, String tableName, String pathToFile) {
		String sqlString = "";
		sqlString += " create table " + schemaName + "." + tableName + " \n";
		sqlString += " using csv \n";
		sqlString += " options ( \n";
		sqlString += "   header = \"true\", \n";
		sqlString += "   inferSchema = \"true\", \n";
		sqlString += "   delimiter = \",\", \n";
		sqlString += "   path = \"" + pathToFile + "\" \n";
		sqlString += " ) \n";
		return sqlString;
	}

}
