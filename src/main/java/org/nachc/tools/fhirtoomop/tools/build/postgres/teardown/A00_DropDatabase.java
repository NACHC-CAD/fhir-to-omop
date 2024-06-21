package org.nachc.tools.fhirtoomop.tools.build.postgres.teardown;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A00_DropDatabase {

	public static void main(String[] args) {
		log.info("Deleting database...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		log.info("Getting connection...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String databaseName = AppParams.get("ohdsiDbName");
			log.info("Dropping database: " + databaseName);
			boolean exists = databaseExists(databaseName, conn);
			if (exists == true) {
				disconnectOtherUsers(databaseName, conn);
				dropDatabase(databaseName, conn);
			}
		} finally {
			log.info("Closing connection...");
			Database.closeConnection(conn);
			log.info("Connection closed.");
		}
		log.info("Done dropping database.");
	}

	//
	// check if the database exists
	//

	private static boolean databaseExists(String databaseName, Connection conn) {
		log.info("Checking if database exists...");
		String sqlString = "";
		sqlString += "SELECT \n";
		sqlString += "    CASE \n";
		sqlString += "        WHEN EXISTS (SELECT 1 FROM pg_database WHERE datname = '" + databaseName + "') THEN 1 \n";
		sqlString += "        ELSE 0 \n";
		sqlString += "    END AS exists \n";
		String existsString = Database.queryForFirst(sqlString, "exists", conn);
		boolean rtn = false;
		if ("1".equals(existsString)) {
			rtn = true;
		}
		log.info("Database exists: " + rtn);
		return rtn;
	}

	//
	// disconnect other users
	//

	private static void disconnectOtherUsers(String databaseName, Connection conn) {
		log.info("Disconnecting other users...");
		String sqlString = "";
		sqlString += "SELECT pg_terminate_backend(pg_stat_activity.pid) \n";
		sqlString += "FROM pg_stat_activity \n";
		sqlString += "WHERE pg_stat_activity.datname = '" + databaseName + "' \n";
		sqlString += "  AND pid <> pg_backend_pid() \n";
		Database.query(sqlString, conn);
		log.info("Done disconnecting other users.");
	}

	//
	// drop the database
	//

	private static void dropDatabase(String databaseName, Connection conn) {
		String sqlString = "drop database \"" + databaseName + "\"";
		log.info("Sql: \n" + sqlString);
		log.info("Dropping database...");
		Database.update(sqlString, conn);
		log.info("Done with drop.");
	}
}
