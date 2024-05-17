package org.nachc.tools.fhirtoomop.util.db.connection;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDatabaseConnectionFactoryManualTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			String sqlString = "select * from concept limit 10";
			Data data = Database.query(sqlString, conn);
			log.info("Got " + data.size() + " records.");
			for(Row row : data) {
				log.info("\t" + row.get("tableName"));
			}
			log.info("Got " + data.size() + " records.");
			assertTrue(data.size() > 0);
			String schemaName = AppParams.getFullySpecifiedSchemaName();
			log.info("Got Schema: " + schemaName);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
}
