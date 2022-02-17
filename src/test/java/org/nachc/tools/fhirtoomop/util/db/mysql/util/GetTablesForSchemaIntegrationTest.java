package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetTablesForSchemaIntegrationTest {

	@Test
	public void shouldGetTables() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Getting tables...");
			String schemaName = "information_schema";
			Data data = GetTablesForSchema.exec(schemaName, conn);
			log.info("Got " + data.size() + " records.");
			for(Row row : data) {
				log.info("\t" + row.get("tableSchema") + "." + row.get("tableName"));
			}
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
