package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetTablesForSchema {

	public static Data exec(String schemaName, Connection conn) {
		String sqlString = "select * from information_schema.tables where table_schema = ?";
		Data data = Database.query(sqlString, schemaName, conn);
		return data;
	}

}
