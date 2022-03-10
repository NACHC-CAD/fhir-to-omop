package org.nachc.tools.fhirtoomop.util.mysql;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.mysql.util.ConvertAllTablesToMyIsam;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertAllTablesInSchemaToMyIsamTool {

	private static final String SCHEMA = AppParams.getFullyQualifiedDbName();

	public static void main(String[] args) {
		log.info("CONVERTING TABLES TO MYISAM");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			new ConvertAllTablesToMyIsam().exec(SCHEMA, conn);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
