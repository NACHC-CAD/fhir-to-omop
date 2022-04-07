package org.nachc.tools.fhirtoomop.util.db.truncatedatatables;

import java.sql.Connection;
import java.util.List;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateDataTables {

	public static void truncateTables(List<String> tableNames, Connection conn) {
		try {
//			Database.update("EXEC sp_MSforeachtable \"ALTER TABLE ? NOCHECK CONSTRAINT all\"", conn);
//			log.info("FOREIGN KEYS DISABLED");
			// delete from the datatables
			for(String tableName : tableNames) {
				String sqlString = "truncate table " + tableName;
				log.info(sqlString);
				Database.update(sqlString, conn);
				log.info("TRUNCATED TABLE: " + tableName);
			}
		} finally {
//			Database.update("EXEC sp_MSforeachtable \"ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all\"", conn);
//			log.info("FOREIGN KEYS ENABLED");
		}
	}
	
}
