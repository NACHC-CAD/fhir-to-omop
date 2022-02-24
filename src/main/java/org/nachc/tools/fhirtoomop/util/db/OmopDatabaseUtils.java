package org.nachc.tools.fhirtoomop.util.db;

import java.sql.Connection;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDatabaseUtils {

	
	public static Integer getCount(String schemaName, String tableName, Connection conn) {
		String sqlString = "select count(*) row_count from " + schemaName + "." + tableName;
		log.info(sqlString);
		Data data = Database.query(sqlString, conn);
		if (data.size() > 0) {
			String str = data.get(0).get("rowCount");
			int rtn = Integer.parseInt(str);
			return rtn;
		} else {
			return null;
		}
	}

}
