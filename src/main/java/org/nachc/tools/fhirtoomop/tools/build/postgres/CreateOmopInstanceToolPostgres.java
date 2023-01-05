package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A01_CreateAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A02_CreateAtlasDatabase;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A03_CreateAtlasWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A04_CreateAchillesDatabases;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A05_CreateCdmSourceRecordInCdmForAtlas;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM01_CreateCdmDatabase;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM02_CreateCdmDatabaseTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM03_CreateFhirResourcesTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM04_LoadTerminology;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR01_CreateMappingTables;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.FHIR02_LoadFhirRaceEthMappings;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOmopInstanceToolPostgres {

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	private static void exec(Connection conn) {
		log.info("CREATING OMOP INSTANCE FOR POSTGRESQL");
		CDM01_CreateCdmDatabase.exec();
		CDM02_CreateCdmDatabaseTables.exec();
		CDM03_CreateFhirResourcesTables.exec();
		FHIR01_CreateMappingTables.exec();
		FHIR02_LoadFhirRaceEthMappings.exec();
		A01_CreateAtlasDatabaseUsers.exec(conn);
		A02_CreateAtlasDatabase.exec(conn);
		A03_CreateAtlasWebApiSchema.exec(conn);
		A04_CreateAchillesDatabases.exec(conn);
		A05_CreateCdmSourceRecordInCdmForAtlas.exec();
		CDM04_LoadTerminology.exec();
		log.info("Done.");
	}

}