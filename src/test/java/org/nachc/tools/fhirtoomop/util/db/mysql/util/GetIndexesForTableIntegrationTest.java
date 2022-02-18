package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.MySqlAuthParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetIndexesForTableIntegrationTest {

	@Test
	public void shouldGetIndexes() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Starting test...");
			String schemaName = MySqlAuthParams.syntheaDb();
			String tableName = "visit_occurrence";
			Data data = GetIndexesForTable.exec(schemaName, tableName, conn);
			for(Row row : data) {
				log.info("\t" + row.get("seqInIndex") + "\t" + row.get("comment") + "\t" + row.get("columnName"));
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
