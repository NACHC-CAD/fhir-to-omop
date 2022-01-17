package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGeneratorIntegrationTest {

	@Test
	public void shouldGetId() {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Starting test...");
			Integer id = FhirToOmopIdGenerator.getId("person", "person_id", conn);
			log.info("Got ID: " + id);
			assertTrue(id != null);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
