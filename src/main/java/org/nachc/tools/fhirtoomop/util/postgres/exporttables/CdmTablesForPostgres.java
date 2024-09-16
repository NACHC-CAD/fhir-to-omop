package org.nachc.tools.fhirtoomop.util.postgres.exporttables;

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
public class CdmTablesForPostgres {

	public static void main(String[] args) {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		try {
			log.info("Getting tables...");
			List<String> tableNames = getAllCdmTables(conn);
			//		List<String> tableNames = getDataTables(conn);
			//		List<String> tableNames = getVocabTables(conn);
			for (String tableName : tableNames) {
				log.info("\t" + tableName);
			}
			log.info("Got " + tableNames.size() + " tables.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static List<String> getAllCdmTables(Connection conn) {
		return getTables(new ArrayList<String>(), false, conn);
	}

	public static List<String> getDataTables(Connection conn) {
		List<String> ignoreList = VocabularyTablesList.getTablesList();
		return getTables(ignoreList, false, conn);
	}

	public static List<String> getVocabTables(Connection conn) {
		List<String> ignoreList = VocabularyTablesList.getTablesList();
		return getTables(ignoreList, true, conn);
	}

	public static List<String> getTables(List<String> ignoreList, boolean invertIgnore, Connection conn) {
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
		Data data = Database.query(sqlString, conn);
		for (Row row : data) {
			String tableName = row.get("tableName");
			if (ignoreList.contains(tableName.toUpperCase()) == false && invertIgnore == false) {
				rtn.add(tableName.toUpperCase());
			}
			if (ignoreList.contains(tableName.toUpperCase()) == true && invertIgnore == true) {
				rtn.add(tableName.toUpperCase());
			}
		}
		return rtn;
	}

}
