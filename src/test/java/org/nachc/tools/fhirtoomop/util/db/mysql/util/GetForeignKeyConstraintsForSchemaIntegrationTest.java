package org.nachc.tools.fhirtoomop.util.db.mysql.util;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetForeignKeyConstraintsForSchemaIntegrationTest {

	private static final String SCHEMA_NAME = "synthea_omop";

	@Test
	public void shouldGetForeignKeys() {
		log.info("Getting data...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			Data data = GetForeignKeyConstraintsForSchema.exec(SCHEMA_NAME, conn);
			log.info("Got " + data.size() + " rows.");
			for(Row row : data) {
				log.info("\t" + row.get("tableSchema") + "\t" + row.get("tableName") + "\t" + row.get("constraintName"));
			}
		} finally {
			MySqlDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
