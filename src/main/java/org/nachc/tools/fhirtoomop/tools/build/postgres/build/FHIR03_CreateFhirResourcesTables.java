package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR03_CreateFhirResourcesTables {

	private static final String FILE_PATH = "/postgres/build/FHIR03_CreateFhirResourcesTables.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating FHIR resource database tables...");
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		log.info("Got connection...");
		try {
			log.info("Running script...");
			Database.executeSqlScript(getSqlString(), conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		log.info("Done creating FHIR resource database tables.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getDatabaseName());
		return sqlString;
	}

}
