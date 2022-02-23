package org.nachc.tools.fhirtoomop.util.db.counts;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetCountsForAllTablesInSchema {

	public static Data getCountsForSchema(String schemaName, Connection conn) {
		String cat = AppConnectionParams.getCatalogPart(schemaName);
		String sch = AppConnectionParams.getSchemaPart(schemaName);
		log.info("Getting counts for: " + cat + "." + sch);
		String sqlString = "select * from information_schema.tables where table_catalog = ? and table_schema = ?";
		String[] params = {cat, sch};
		Data data = Database.query(sqlString, params, conn);
		ArrayList<String> tableNames = new ArrayList<String>();
		for (Row row : data) {
			String tableName = row.get("tableName");
			tableNames.add(tableName);
		}
		return getCountsForTables(schemaName, tableNames, conn);
	}

	public static Data getCountsForTables(String schemaName, List<String> tableNames, Connection conn) {
		Data data = new Data();
		for (String tableName : tableNames) {
			log.debug("Getting counts for: " + schemaName + "." + tableName);
			String sqlString = "select '" + schemaName + "." + tableName + "' as table_name, count(*) as row_count from " + schemaName + "." + tableName + "\n";
			log.debug(sqlString);
			Data dataForTable = Database.query(sqlString, conn);
			log.debug("CNT = " + dataForTable.get(0).get("rowCount"));
			data.addAll(dataForTable);
		}
		return data;
	}

}
