package org.nachc.tools.fhirtoomop.omop.util.id.fixer;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixSequencesIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	// @Test
	public void shouldResetSequence() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			log.info("Setting concept sequence to 0");
			String dbName = getDbName();
			Database.update("alter sequence concept_concept_id restart with 1", conn);
			Database.commit(conn);
			log.info("Resetting sequences...");
			FixSequences.exec();
			Database.commit(conn);
		} finally {
			log.info("Closing connection...");
			OmopDatabaseConnectionFactory.close(conn);
			log.info("Connection closed.");
		}
		log.info("Done.");
	}
	
	private String getDbName() {
		String schema = AppParams.getFullySpecifiedCdmSchemaName();
		if (schema.trim().endsWith(".dbo")) {
			schema = schema.substring(0, schema.indexOf(".dbo"));
		}
		return schema;
	}
	
}
