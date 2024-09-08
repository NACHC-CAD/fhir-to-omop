package org.nachc.tools.fhirtoomop.tools.build.postgres.teardown;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DropSchemasForPostgres {

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		String cdmName = AppParams.getFullySpecifiedCdmSchemaName();
		String resName = AppParams.getFullySpecifiedAchillesResultsSchemaName();
		String tempName = AppParams.getFullySpecifiedAchillesTempSchemaName();
		String synthDbName = AppParams.getSyntheaCsvNativeDatabase();
		String webApiName = AppParams.getPostgresWebApiSchemaName();
		dropSchema(cdmName, conn);
		dropSchema(resName, conn);
		dropSchema(tempName, conn);
		dropSchema(synthDbName, conn);
		dropSchema(webApiName, conn);
		Database.commit(conn);
		log.info("Done tearing down Achilles databases.");
	}

	private static void dropSchema(String schemaName, Connection conn) {
		log.info("Dropping schema: " + schemaName);
		Database.update("drop schema if exists " + schemaName + " cascade", conn);
	}

}
