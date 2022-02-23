package org.nachc.tools.fhirtoomop.tools.mysql;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.mysql.util.DeleteAllForeignKeysForSchema;
import org.nachc.tools.fhirtoomop.util.params.MySqlAuthParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DropForeignKeysTool {

	private static final String SCHEMA_NAME = MySqlAuthParams.getSyntheaDb();

	public static void main(String[] args) {
		log.info("Dropping all foreign keys for " + SCHEMA_NAME);
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			DeleteAllForeignKeysForSchema.exec(SCHEMA_NAME, conn);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
