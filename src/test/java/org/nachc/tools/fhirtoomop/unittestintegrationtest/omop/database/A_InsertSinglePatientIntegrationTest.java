package org.nachc.tools.fhirtoomop.unittestintegrationtest.omop.database;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.InsertSinglePatient;
import org.nachc.tools.fhirtoomop.util.db.OmopDatabaseUtils;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A_InsertSinglePatientIntegrationTest {

	@Test
	public void shouldInsertSinglePatient() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			// get the params
			String schemaName = AppConnectionParams.getFullyQualifiedDbName();
			String tableName = "person";
			int personCount;
			// get the patient count
			log.info("Getting count...");
			personCount = OmopDatabaseUtils.getCount(schemaName, tableName, conn);
			log.info("personCount before: " + personCount);
			// insert the patient
			InsertSinglePatient.exec(conn);
			// get the count
			log.info("Getting count...");
			personCount = OmopDatabaseUtils.getCount(schemaName, tableName, conn);
			log.info("personCount after: " + personCount);
		} finally {
			log.info("Closing connection...");
			Database.close(conn);
			log.info("Connection closed.");
		}
		log.info("Done.");
	}
	
}
