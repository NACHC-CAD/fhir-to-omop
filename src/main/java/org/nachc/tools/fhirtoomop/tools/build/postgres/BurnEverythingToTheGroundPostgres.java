package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.BurnAtlasToTheGround;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGroundPostgres {

	public static void main(String[] args) {
		log.info("Burning everything to the ground...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	private static void exec(Connection conn) {
		doSqlDatabaseDrops(conn);
		BurnAtlasToTheGround.exec();
		log.info("Done burning everything to the ground.");
	}
	
	
	private static void doSqlDatabaseDrops(Connection conn) {
		// drop the database
		String databaseName = AppParams.getFullyQualifiedDbName();
		databaseName = AppParams.getCatalogPart(databaseName);
		log.warn("DROPPING DATABASE: " + databaseName);
		Database.update("drop database if exists " + databaseName, conn);
		Database.update("drop database if exists " + databaseName + "_dqd_results", conn);
		log.warn("DATABASE DROPPED: " + databaseName);
		// drop the login
		String uid = AppParams.getUid();
		log.warn("DROPPING LOGIN: " + uid);
		/*
		boolean loginExists = loginExists(conn, uid);
		if (loginExists) {
			log.info("Doing drop...");
			Database.update("drop login " + uid, conn);
			log.info("Done with drop.");
		}
		log.warn("LOGIN DROPPED: " + uid);
		*/
		log.info("Done.");
	}

	/*
	private static boolean loginExists(Connection conn, String uid) {
		String sqlString = "select * from sys.server_principals sp where sp.name = ?";
		Data data = Database.query(sqlString, uid, conn);
		if (data.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	*/

}
