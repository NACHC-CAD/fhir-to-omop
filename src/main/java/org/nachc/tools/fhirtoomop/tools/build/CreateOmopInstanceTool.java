package org.nachc.tools.fhirtoomop.tools.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.impl.AddConstraints;
import org.nachc.tools.fhirtoomop.tools.build.impl.BurnEverythingToTheGround;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateCdmSourceRecord;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabase;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseIndexes;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateDatabaseUser;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateFhirResoureTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.CreateSequencesForPrimaryKeys;
import org.nachc.tools.fhirtoomop.tools.build.impl.LoadMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.impl.LoadTerminology;
import org.nachc.tools.fhirtoomop.tools.build.impl.MoveRaceEthFiles;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates an instance of OMOP from scratch based on the settings in
 * the app.properties file. You will also need to provide the following files
 * (see the readme.txt files in these folders for what needs to be in each)
 * 
 * /src/main/resources/auth/app.properties
 *
 */

@Slf4j
public class CreateOmopInstanceTool {

	public static void main(String[] args) {
		createOmopInstance();
	}

	public static void createOmopInstance() {
		log.info("Creating MS SqlServer OMOP instance...");
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		Timer timer = new Timer();
		try {
			timer.start();
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
			// create the cdm_source record (uses app.parameters values)
			CreateCdmSourceRecord.exec(conn);
			Database.commit(conn);
			// move the race eth files
			MoveRaceEthFiles raceFiles = new MoveRaceEthFiles();
			raceFiles.exec();
			// load the terminologies
			logMsg("LOADING TERMINOLOGY");
			LoadMappingTables.exec(raceFiles.getSqlFile(), conn);
			LoadTerminology.exec(conn);
			// create the sequences
			logMsg("CREATING SEQUENCES");
			CreateSequencesForPrimaryKeys.exec(conn);
			// create the indexes and add constraints
			CreateDatabaseIndexes.exec(conn);
			AddConstraints.exec();
			timer.stop();
			log.info("Done creating instance");
			log.info("----------------");
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
		msg += "\n\n";
		msg += "\n---------------";
		msg += "\nYour instance has been created with the following parameters:  ";
		msg += "\nDatbase:\t" + dbName;
		msg += "\nUsername:\t" + uid;
		msg += "\nPassword\t" + pwd;
		msg += "\nHave a great odyssey :)";
		msg += "\n---------------";
		msg += "\nstart:   " + timer.getStartAsString();
		msg += "\nstop:    " + timer.getStopAsString();
		msg += "\nelapsed: " + timer.getElapsedString();
		msg += "\n---------------";
		msg += "\n";
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
