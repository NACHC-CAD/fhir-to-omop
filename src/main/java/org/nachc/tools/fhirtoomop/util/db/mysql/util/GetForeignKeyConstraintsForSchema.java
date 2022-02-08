package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

public class GetForeignKeyConstraintsForSchema {

	public static Data exec(String schemaName, Connection conn) {
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "	table_schema, \n";
		sqlString += "	table_name, \n";
		sqlString += "    constraint_name \n";
		sqlString += "from \n";
		sqlString += "	information_schema.key_column_usage \n";
		sqlString += "where 1=1 \n";
		sqlString += "	and constraint_schema = ? \n";
		sqlString += "	and referenced_table_name is not null \n";
		Data data = Database.query(sqlString, schemaName, conn);
		return data;
	}

}
