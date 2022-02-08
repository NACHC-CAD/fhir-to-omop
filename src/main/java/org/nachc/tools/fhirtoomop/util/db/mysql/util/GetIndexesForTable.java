package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

public class GetIndexesForTable {

	public static Data exec(String schemaName, String tableName, Connection conn) {
		String[] params = { schemaName, tableName };
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "  table_name, \n";
		sqlString += "  index_name, \n";
		sqlString += "  seq_in_index, \n";
		sqlString += "  column_name, \n";
		sqlString += "  non_unique, \n";
		sqlString += "  index_type, \n";
		sqlString += "  comment \n";
		sqlString += "from \n";
		sqlString += "	information_schema.statistics \n";
		sqlString += "where 1=1 \n";
		sqlString += "	and table_schema = ? \n";
		sqlString += "    and table_name = ? \n";
		sqlString += "order by 1,2,3,4,5,6 \n";
		Data data = Database.query(sqlString, params, conn);
		return data;
	}
	
	/*
	-- MS SQL SERVER VERSION
	SELECT 
	     TableName = t.name,
	     IndexName = ind.name,
	     IndexId = ind.index_id,
	     ColumnId = ic.index_column_id,
	     ColumnName = col.name,
	     ind.*,
	     ic.*,
	     col.* 
	FROM 
	     sys.indexes ind 
	INNER JOIN 
	     sys.index_columns ic ON  ind.object_id = ic.object_id and ind.index_id = ic.index_id 
	INNER JOIN 
	     sys.columns col ON ic.object_id = col.object_id and ic.column_id = col.column_id 
	INNER JOIN 
	     sys.tables t ON ind.object_id = t.object_id 
	WHERE 
	     ind.is_primary_key = 0 
	     AND ind.is_unique = 0 
	     AND ind.is_unique_constraint = 0 
	     AND t.is_ms_shipped = 0 
	ORDER BY 
	     t.name, ind.name, ind.index_id, ic.is_included_column, ic.key_ordinal;
	 */

}
