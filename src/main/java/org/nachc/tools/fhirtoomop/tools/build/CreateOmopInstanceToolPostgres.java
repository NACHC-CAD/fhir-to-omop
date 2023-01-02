package org.nachc.tools.fhirtoomop.tools.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.AddConstraintsPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.BurnEverythingToTheGroundPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateCdmSourceRecordPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateDatabaseIndexesPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateDatabasePostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateDatabaseTablesPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateFhirResoureTablesPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateMappingTablesPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateSequencesForPrimaryKeysPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.LoadMappingTablesPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.LoadTerminologyPostgres;
import org.nachc.tools.fhirtoomop.tools.build.postgres.MoveRaceEthFilesPostgres;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
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
public class CreateOmopInstanceToolPostgres {

	public static void main(String[] args) {
		createOmopInstance();
	}

	public static void createOmopInstance() {
		Connection conn = null;
		Timer timer = new Timer();
		try {
			timer.start();
			// delete the existing instance
			logMsg("BURNING EVERYTHING TO THE GROUND");
			BurnEverythingToTheGroundPostgres.main(null);
			// create the new database
			logMsg("CREATING DATABASE");
			log.info("Creating OMOP instance...");
			CreateDatabasePostgres.main(null);
			conn = PostgresDatabaseConnectionFactory.getDbConnection();
			// create the user
			//			logMsg("CREATING USER");
			//			CreateDatabaseUser.exec(conn);
			// create the tables
			logMsg("CREATING TABLES");
			CreateDatabaseTablesPostgres.exec(conn);
			CreateFhirResoureTablesPostgres.main(null);
			CreateMappingTablesPostgres.main(null);
			// create the cdm_source record (uses app.parameters values)
			CreateCdmSourceRecordPostgres.main(null);
			Database.commit(conn);
			// move the race eth files
			MoveRaceEthFilesPostgres raceFiles = new MoveRaceEthFilesPostgres();
			raceFiles.exec();
			// load the terminologies
			logMsg("LOADING TERMINOLOGY");
			LoadMappingTablesPostgres.exec(raceFiles.getSqlFile(), conn);
			LoadTerminologyPostgres.main(null);
			// create the sequences
			logMsg("CREATING SEQUENCES");
			CreateSequencesForPrimaryKeysPostgres.main(null);
			// create the indexes and add constraints
			AddConstraintsPostgres.exec(conn);
			CreateDatabaseIndexesPostgres.main(null);
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
