package org.nachc.tools.fhirtoomop.tools.build.postgres.util.alltables;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllTablesForSchemaPostgres {

	public static List<String> getAllTableNamesForSchema(String schemaName, Connection conn) {
		ArrayList<String> rtn = new ArrayList<String>();
		String sqlString = "";
		sqlString += "select distinct table_name from information_schema.tables \n";
		sqlString += "where table_schema = ? \n";
		log.info("\n" + sqlString);
		Data data = Database.query(sqlString, schemaName, conn);
		log.info("Got " + data.size() + " tables.");
		for(Row row : data) {
			String tableName = row.get("tableName");
			rtn.add(tableName);
		}
		return rtn;
	}

}
