package org.nachc.tools.fhirtoomop.tools.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.impl.BurnEverythingToTheGround;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabase;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseIndexes;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseUser;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateFhirResoureTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.LoadMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.LoadTerminology;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates an instance of OMOP from scratch based on the settings in
 * the app.properties file. You will also need to provide the following files
 * (see the readme.txt files in these folders for what needs to be in each)
 * 
 * /src/main/resources/auth/app-auth.properties
 * /src/main/resources/auth/synthea-auth.properties
 *
 */

@Slf4j
public class CreateOmopInstanceTool {

	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			// delete the existing instance
			logMsg("BURNING EVERYTHING TO THE GROUND");
			BurnEverythingToTheGround.exec(conn);
			// create the new database
			logMsg("CREATING DATABASE");
			log.info("Creating OMOP instance...");
			CreateDatabase.exec(conn);
			// create the user
			logMsg("CREATING USER");
			CreateDatabaseUser.exec(conn);
			// create the tables
			logMsg("CREATING TABLES");
			CreateDatabaseTables.exec(conn);
			CreateFhirResoureTables.exec(conn);
			CreateMappingTables.exec(conn);
			// load terminology
			logMsg("LOADING TERMINOLOGY");
			LoadMappingTables.exec(conn);
			LoadTerminology.exec(conn);
			// create the indexes
			CreateDatabaseIndexes.exec(conn);
		} finally {
			log.info("Closing database connection...");
			Database.close(conn);
			log.info("Database closed");
		}
		logMsg("DONE");
		String dbName = AppParams.getDbName();
		String uid = AppParams.getUid();
		String pwd = AppParams.getPwd();
		String msg = "";
		msg += "\n\n\n";
		msg += "\n---------------";
		msg += "\nYour instance has been created with the following parameters:  ";
		msg += "\nDatbase:\t" + dbName;
		msg += "\nUsername:\t" + uid;
		msg += "\nPassword\t" + pwd;
		msg += "\nYour welcome :)";
		msg += "\n---------------";
		msg += "\n\n\n";
		log.info(msg);
		log.info("Done.");
	}

	private static void logMsg(String msg) {
		String logMsg = "";
		logMsg += "\n\n\n";
		logMsg += "\n-----------------------------------------------------------";
		logMsg += "\n! ! ! " + msg + " ! ! ! ";
		logMsg += "\n-----------------------------------------------------------";
		logMsg += "\n\n\n";
		log.info(logMsg);
	}

}
