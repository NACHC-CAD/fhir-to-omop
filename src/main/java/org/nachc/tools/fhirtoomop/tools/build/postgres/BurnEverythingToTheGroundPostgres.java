package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.DropDatabaseForPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.DropDatabaseUsersForPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.DropSchemasForPostgres;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGroundPostgres {

	public static void main(String[] args) {
		log.info("Burning everything to the ground...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		log.info("BURNING POSTGRES OMOP INSTANCE TO THE GROUND...");
		DropDatabaseUsersForPostgres.exec(conn);
		DropSchemasForPostgres.exec(conn);
		DropDatabaseForPostgres.exec(conn);
		log.info("Done burning everything to the ground.");
	}

}
