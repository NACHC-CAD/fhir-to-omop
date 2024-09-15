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
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		log.info("Got connection...");
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating mapping tables.");
	}

	public static void exec(Connection conn) {
		log.info("Creating mapping tables...");
		log.info("Got connection...");
		log.info("Running script...");
		String sqlString = getSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done running script.");
		log.info("Done creating mapping tables.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getDatabaseName());
		return sqlString;
	}

}
