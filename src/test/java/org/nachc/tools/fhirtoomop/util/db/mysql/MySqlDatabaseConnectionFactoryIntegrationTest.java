package org.nachc.tools.fhirtoomop.util.db.mysql;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySqlDatabaseConnectionFactoryIntegrationTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		String sqlString = "select table_name from information_schema.tables where table_schema = 'information_schema' order by table_name";
		Data data = Database.query(sqlString, conn);
		log.info("Got " + data.size() + " records.");
		for(Row row : data) {
			log.info("\t" + row.get("tableName"));
		}
		log.info("Got " + data.size() + " records.");
		assertTrue(data.size() > 10);
		log.info("Done.");
	}
	
}
