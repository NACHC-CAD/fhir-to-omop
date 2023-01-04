package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A01_CreateAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A02_CreateAtlasDatabase;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A03_CreateAtlasWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A04_CreateAchillesDatabases;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.CDM01_CreateDatabase;
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
		A01_CreateAtlasDatabaseUsers.exec(conn);
		A02_CreateAtlasDatabase.exec(conn);
		A03_CreateAtlasWebApiSchema.exec(conn);
		A04_CreateAchillesDatabases.exec(conn);
		CDM01_CreateDatabase.exec();
		log.info("Done.");
	}

}
