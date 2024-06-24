package org.nachc.tools.fhirtoomop.util.sqlserver;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CdmTables {

	public static void main(String[] args) {
		log.info("Getting tables...");
		List<String> tableNames = getAllCdmTables();
//		List<String> tableNames = getDataTables();
//		List<String> tableNames = getVocabTables();
		for(String tableName : tableNames) {
			log.info("\t" + tableName);
		}
		log.info("Got " + tableNames.size() + " tables.");
		log.info("Done.");
	}

	public static List<String> getAllCdmTables() {
		return getTables(new ArrayList<String>(), false);
	}
	
	public static List<String> getDataTables() {
		List<String> ignoreList = VocabularyTablesList.getTablesList();
		return getTables(ignoreList, false);
	}
	
	public static List<String> getVocabTables() {
		List<String> ignoreList = VocabularyTablesList.getTablesList();
		return getTables(ignoreList, true);
	}
	
	public static List<String> getTables(List<String> ignoreList, boolean invertIgnore) {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		try {
			ArrayList<String> rtn = new ArrayList<String>();
			String dbName = AppParams.getDatabaseName();
			String schemaName = AppParams.getSchemaName();
			String sqlString = "";
			sqlString += "select table_name \n";
			sqlString += "from information_schema.tables \n";
			sqlString += "where lower(table_schema) = lower('" + schemaName + "') \n";
			sqlString += "and lower(table_catalog) = lower('" + dbName + "') \n";
			sqlString += "and lower(table_type) = lower('BASE TABLE') \n";
			log.info("sqlString: \n" + sqlString);
			Database.update("use " + dbName, conn);
			Data data = Database.query(sqlString, conn);
			for(Row row : data) {
				String tableName = row.get("tableName");
				if(ignoreList.contains(tableName.toUpperCase()) == false && invertIgnore == false) {
					rtn.add(tableName.toUpperCase());
				}
				if(ignoreList.contains(tableName.toUpperCase()) == true && invertIgnore == true) {
					rtn.add(tableName.toUpperCase());
				}
			}
			return rtn;
		} finally {
			Database.close(conn);
		}
	}

}
