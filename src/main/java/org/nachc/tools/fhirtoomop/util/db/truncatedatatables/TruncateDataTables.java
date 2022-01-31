package org.nachc.tools.fhirtoomop.util.db.truncatedatatables;

import java.sql.Connection;
import java.util.List;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateDataTables {

	public static void truncateTables(List<String> tableNames, Connection conn) {
		try {
			Database.update("set foreign_key_checks = 0", conn);
			log.info("FOREIGN_KEY_CHECKS SET TO 0");
			for(String tableName : tableNames) {
				String sqlString = "truncate table " + tableName;
				Database.update(sqlString, conn);
				log.info("TRUNCATED TABLE: " + tableName);
			}
		} finally {
			Database.update("set foreign_key_checks = 1", conn);
			log.info("FOREIGN_KEY_CHECKS SET TO 1");
		}
	}
	
}
