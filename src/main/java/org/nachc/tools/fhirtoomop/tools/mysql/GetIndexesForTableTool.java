package org.nachc.tools.fhirtoomop.tools.mysql;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.mysql.util.GetIndexesForTable;
import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetIndexesForTableTool {

	private static final String SCHEMA = AppConnectionParams.getSyntheaDb();
	
	private static final String TABLE = "visit_occurrence";

	public static void main(String[] args) {
		log.info("Getting data...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			Data data = GetIndexesForTable.exec(SCHEMA, TABLE, conn);
			for(Row row : data) {
				log.info("\t" + row.get("seqInIndex") + "\t" + row.get("comment") + "\t" + row.get("columnName"));
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
