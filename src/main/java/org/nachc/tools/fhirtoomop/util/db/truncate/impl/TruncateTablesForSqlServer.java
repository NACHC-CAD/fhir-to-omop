package org.nachc.tools.fhirtoomop.util.db.truncate.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.db.truncate.TruncateTables;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateTablesForSqlServer extends TruncateTables {

	//
	// main method (for manual testing)
	//

	public static void main(String[] args) {
		// truncateAllTables();
		// truncateDataTables();
		// truncateVocabularyTables();
	}

	//
	// method to get all of the tables for a schema
	//
	
	public List<String> getTablesForSchema(String dbName, String schemaName, Connection conn) {
		if (allTables == null) {
			allTables = new ArrayList<String>();
			String sqlString = "";
			sqlString += "select table_name from information_schema.tables \n";
			sqlString += "where table_catalog = '" + dbName + "' \n";
			sqlString += "and table_schema = '" + schemaName + "' \n";
			Database.update("use " + dbName, conn);
			Data data = Database.query(sqlString, conn);
			for (Row row : data) {
				String str = row.get("tableName");
				allTables.add(str);
			}
		}
		return allTables;
	}

}
