package org.nachc.tools.fhirtoomop.util.db.counts;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetCountsForDataTablesInSchemaIntegrationTest {
	
	private static final String SCHEMA_NAME = AppConnectionParams.getFullyQualifiedDbName();
	
	private static final List<String> TABLES = DatatableList.getDatatableList();

	@Test
	public void shouldGetCounts() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			Data data = GetCountsForAllTablesInSchema.getCountsForTables(SCHEMA_NAME, TABLES, conn);
			log.info("Got " + data.size() + " records");
		} finally {
			log.info("Closing database");
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
