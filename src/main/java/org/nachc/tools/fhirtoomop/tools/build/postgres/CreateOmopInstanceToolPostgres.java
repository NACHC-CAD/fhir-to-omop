package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A01_CreateAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A03_CreateAtlasWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A04_CreateAtlasWebApiTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A05_CreateAchillesDatabases;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A06_CreateAchillesTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A07_GrantPrivileges;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A08_CreateAtlasSourceRecordsInWebApi;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.ACH00_InstallAchilles;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM01b_CreateCdmSchema;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM02a_CreateCdmDatabaseTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.ETLSYN00_LoadDevTools;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR01_CreateMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR02_LoadFhirRaceEthMappings;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR03_CreateFhirResourcesTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR04_AddPlaceholderCdmRecords;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR05_CreateSequencesForPrimaryKeys;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR06a_CreateSyntheaNative;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR06b_CreateSyntheaNativeDatabaseTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.IDX01_CreateCdmPrimaryKeys;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.IDX02_CreateCdmIndexes;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.VOC00_DownloadTerminology;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.VOC99_LoadTerminology;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOmopInstanceToolPostgres {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("! ! ! BURNING EVERYTHING TO THE GROUND ! ! !");
		BurnEverythingToTheGroundPostgres.exec();
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	private static void exec(Connection conn) {
		Timer timer = new Timer();
		timer.start();
		log.info("CREATING OMOP INSTANCE FOR POSTGRESQL...");
		log.info("! ! ! CREATING OMOP INSTANCE FOR POSTGRESQL ! ! !");
		// do the dependency installs up front so we can see if something goes awry early in the build
		ACH00_InstallAchilles.exec();
		ETLSYN00_LoadDevTools.exec();
		VOC00_DownloadTerminology.exec();
		// do the install
		A01_CreateAtlasDatabaseUsers.exec(conn);
		//		A02_CreateAtlasDatabase.exec(conn);
		A03_CreateAtlasWebApiSchema.exec();
		A04_CreateAtlasWebApiTables.exec();
		A05_CreateAchillesDatabases.exec();
		A06_CreateAchillesTables.exec();
		A07_GrantPrivileges.exec();
		A08_CreateAtlasSourceRecordsInWebApi.exec();
		CDM01b_CreateCdmSchema.exec();
		CDM02a_CreateCdmDatabaseTables.exec();
		//		CDM02b_CreateCdmDatabasePrimaryKeys.exec();
		//		CDM03_CreateCdmSourceRecordInCdmForAtlas.exec();
		FHIR01_CreateMappingTables.exec();
		FHIR02_LoadFhirRaceEthMappings.exec();
		FHIR03_CreateFhirResourcesTables.exec();
		FHIR04_AddPlaceholderCdmRecords.exec();
		FHIR05_CreateSequencesForPrimaryKeys.exec();
		FHIR06a_CreateSyntheaNative.exec();
		FHIR06b_CreateSyntheaNativeDatabaseTables.exec();
		VOC99_LoadTerminology.exec();
		IDX01_CreateCdmPrimaryKeys.exec();
		IDX02_CreateCdmIndexes.exec();
		//		IDX03_CreateCdmConstraints.exec();
		// NEXT: LOAD DATA, RUN ACHILLES, BUILD WEB-API, DEPLOY APPLICATIONS

		//		ETLSYN01_LoadSynthFiles.exec();
		//		ETLSYN02_CreateIndexes.exec();
		//		ETLSYN03_EtlSyntheaToCdm.exec();

		//		ACH1_RunAchilles.exec();
		timer.stop();
		log.info("\n\nBUILD TIME: " + timer.getElapsedString());
		log.info("Done.");
	}

}
