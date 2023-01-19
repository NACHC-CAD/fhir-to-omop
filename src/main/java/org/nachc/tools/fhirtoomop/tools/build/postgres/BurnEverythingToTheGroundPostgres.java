package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A01_CreateAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.build.postgres.build.A02_CreateAtlasDatabase;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.A01_TearDownAtlasDatabaseUsers;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.A02_TearDownAtlasDatabase;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.A03_TearDownAtlasWebApiSchema;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.A04_TearDownAchillesDatabases;
import org.nachc.tools.fhirtoomop.tools.build.postgres.teardown.CDM01_TeardownDatabase;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateSyntheaNativeSchema;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnEverythingToTheGroundPostgres {

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	private static void exec(Connection conn) {
		log.info("BURNING POSTGRES OMOP INSTANCE TO THE GROUND...");
		A04_TearDownAchillesDatabases.exec(conn);
		A03_TearDownAtlasWebApiSchema.exec(conn);
		A02_TearDownAtlasDatabase.exec(conn);
		A01_TearDownAtlasDatabaseUsers.exec(conn);
		CDM01_TeardownDatabase.exec();
		TruncateSyntheaNativeSchema.exec();
		log.info("Done.");
	}

}
