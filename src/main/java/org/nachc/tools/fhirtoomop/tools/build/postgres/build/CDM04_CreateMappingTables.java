package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM04_CreateMappingTables {

	private static final String SQL = FileUtil.getAsString("/postgres/build/CDM04_CreateMappingTables.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			log.info("Creating mapping tables...");
			log.info("Running script...");
			Database.executeSqlScript(SQL, conn);
			log.info("Done running script.");
			log.info("Done creating mapping tables.");
		} finally {
			Database.close(conn);
		}
	}

}
