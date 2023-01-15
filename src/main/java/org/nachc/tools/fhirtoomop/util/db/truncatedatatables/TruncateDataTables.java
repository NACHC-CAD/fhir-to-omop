package org.nachc.tools.fhirtoomop.util.db.truncatedatatables;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.ConceptCache;
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
				log.info("TRUNCATING TABLE: " + tableName);
				String sqlString = "delete from " + tableName;
				log.info(sqlString);
				Database.update(sqlString, conn);
				log.info("TRUNCATED TABLE: " + tableName);
			}
			// delete two-billionaires
			log.info("Deleting two-billionaires...");
			Database.update("delete from concept where concept_id > 2000000000", conn);
			// clean the cache
			log.info("Cleaning cache...");
			ConceptCache.reset();
			log.info("Doing commit...");
			Database.commit(conn);
			log.info("Done with deletes, moving on...");
		} finally {
//			log.info("ENABLING FOREIGN KEYS...");
//			Database.update("EXEC sp_MSforeachtable \"ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all\"", conn);
//			log.info("FOREIGN KEYS ENABLED");
		}
	}
	
}
