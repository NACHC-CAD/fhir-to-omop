package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.atlas.impl.BurnAtlasToTheGround;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGround {

	public static void main(String[] args) {
		log.info("Burning everything to the ground...");
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} catch(Throwable thr) {
			throw(new RuntimeException(thr));
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		doSqlServerDrop(conn);
		BurnAtlasToTheGround.exec();
		log.info("Done burning everything to the ground.");
	}
	
	
	public static void doSqlServerDrop(Connection conn) {
		// drop the database
		String databaseName = AppParams.getFullySpecifiedCdmSchemaName();
		databaseName = AppParams.getDatabasePart(databaseName);
		String dqdDatabaseName = AppParams.getDqdResultsSchemaName();
		log.warn("DROPPING DATABASE: " + databaseName);
		Database.update("use master", conn);
		Database.update("drop database if exists " + databaseName, conn);
		Database.update("drop database if exists " + dqdDatabaseName, conn);
		Database.update("drop database if exists " + AppParams.getAchillesResultsSchemaName(), conn);
		Database.update("drop database if exists " + AppParams.getAchillesTempSchemaName(), conn);
		Database.update("drop database if exists " + AppParams.getSyntheaCsvNativeDatabase(), conn);
		log.warn("DATABASE DROPPED: " + databaseName);
		String uid;
		boolean loginExists = false;
		// drop the login for the cdm user
		uid = AppParams.getUid();
		log.warn("DROPPING LOGIN: " + uid);
		loginExists = loginExists(conn, uid);
		if (loginExists) {
			log.info("Doing drop...");
			Database.update("drop login " + uid, conn);
			log.info("Done with drop.");
		}
		log.warn("LOGIN DROPPED: " + uid);
		// drop the login for the synthea csv user
		uid = AppParams.getSyntehsCsvUid();
		log.warn("DROPPING LOGIN: " + uid);
		loginExists = loginExists(conn, uid);
		if (loginExists) {
			log.info("Doing drop...");
			Database.update("drop login " + uid, conn);
			log.info("Done with drop.");
		}
		log.warn("LOGIN DROPPED: " + uid);
		log.info("Done.");
	}

	private static boolean loginExists(Connection conn, String uid) {
		String sqlString = "select * from sys.server_principals sp where sp.name = ?";
		Data data = Database.query(sqlString, uid, conn);
		if (data.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
