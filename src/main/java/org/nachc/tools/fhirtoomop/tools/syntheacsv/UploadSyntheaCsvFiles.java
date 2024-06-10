package org.nachc.tools.fhirtoomop.tools.syntheacsv;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseUser;
import org.nachc.tools.fhirtoomop.tools.syntheacsv.impl.FindDataFiles;
import org.nachc.tools.fhirtoomop.tools.syntheacsv.impl.FindDriver;
import org.nachc.tools.fhirtoomop.tools.syntheacsv.impl.UploadSyntheaCsvFilesToOhdsi;
import org.nachc.tools.fhirtoomop.util.db.connection.mssql.MsSqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadSyntheaCsvFiles {

	public static void main(String[] args) {
		log.info("Finding resources...");
		FindDriver.exec();
		FindDataFiles.exec();
		log.info("Uploading Synthea CSV files...");
		Connection conn = MsSqlDatabaseConnectionFactory.getBootstrapConnection();
		MsSqlDatabaseConnectionFactory.getCdmConnection();
		exec(conn);
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String databaseName = AppParams.getSyntheaCsvNativeDatabase();
		dropDatabase(databaseName, conn);
		createDatabase(databaseName, conn);
		UploadSyntheaCsvFilesToOhdsi.exec();
	}

	private static boolean databaseExists(String databaseName, Connection conn) {
		String sqlString = "SELECT * FROM sys.databases WHERE name = '" + databaseName + "'";
		Data data = Database.query(sqlString, conn);
		if(data.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void dropDatabase(String databaseName, Connection conn) {
		boolean databaseExists = databaseExists(databaseName, conn);
		if(databaseExists) {
			log.info("Dropping database: " + databaseName);
			String sqlString = "drop database " + databaseName;
			log.info("Droping database: \n" + sqlString);
			Database.update(sqlString, conn);
			log.info("Done dropping database.");
		} else {
			log.info("Database doesn't exist, skipping drop.");
		}
		log.info("Done with drop.");
	}
	
	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		String sqlString = "create database " + databaseName;
		Database.update(sqlString, conn);
		log.info("Creating user...");
		createUser(databaseName, conn);
		log.info("Granting permissions...");
		log.info("Done creating database.");
	}
	
	private static void createUser(String databaseName, Connection conn) {
		String db = AppParams.getSyntheaCsvNativeDatabase();
		String uid = AppParams.getSyntehsCsvUid();
		String pwd = AppParams.getSyntehsCsvPwd();
		String sqlString;
		sqlString = "use " + db;
		log.info("sqlString: " + sqlString);
		Database.update(sqlString, conn);
		CreateDatabaseUser.exec(conn, db, uid, pwd);
	}
	
}
