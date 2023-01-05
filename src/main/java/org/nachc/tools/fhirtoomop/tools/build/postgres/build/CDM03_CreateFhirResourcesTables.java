package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM03_CreateFhirResourcesTables {

	private static final String SQL = FileUtil.getAsString("/postgres/build/CDM02_CreateFhirResourcesTables.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating FHIR resource database tables...");
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			log.info("Running script...");
			Database.executeSqlScript(SQL, conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		log.info("Done creating FHIR resource database tables.");
	}

}
