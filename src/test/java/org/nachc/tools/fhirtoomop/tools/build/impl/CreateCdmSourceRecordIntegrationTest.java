package org.nachc.tools.fhirtoomop.tools.build.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateCdmSourceRecordIntegrationTest {

	@Test
	public void shouldCreateRecord() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			// delete any existing records
			Database.update("truncate table cdm_source", conn);
			// create the record twice and assert one record has been created
			CreateCdmSourceRecord.exec(conn);
			CreateCdmSourceRecord.exec(conn);
			// commit the transaction
			Database.commit(conn);
			// assert a single record exists
			int cnt = Database.count("cdm_source", conn);
			log.info("Records in cdm_source: " + cnt);
			assertTrue(cnt == 1);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}
