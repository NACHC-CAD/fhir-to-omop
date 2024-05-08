package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR01_CreateMappingTables {

	private static final String FILE_PATH = "/postgres/build/FHIR01_CreateMappingTables.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating mapping tables...");
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		log.info("Got connection...");
		try {
			log.info("Running script...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		log.info("Done creating mapping tables.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getSchemaName());
		return sqlString;
	}

}
