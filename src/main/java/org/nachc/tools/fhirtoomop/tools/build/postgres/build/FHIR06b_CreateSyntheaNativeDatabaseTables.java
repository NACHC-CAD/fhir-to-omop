package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR06b_CreateSyntheaNativeDatabaseTables {

	private static final String SQL = FileUtil.getAsString("/postgres/build/synthea/ddl/v270/create_synthea_tables.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("START: Creating SYNTHEA_NATIVE database tables...");
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		log.info("Got connection...");
		try {
			log.info("Running script...");
			String dbName = AppParams.get("syntheaNative");
			log.info("DB NAME: " + dbName);
			String sqlString = SQL;
			sqlString = sqlString.replace("@synthea_schema", dbName);
			log.info("Running script:\n\n" + sqlString + "\n\n");
			Database.executeSqlScript(sqlString, conn);
			log.info("Sql has been executed.");
		} finally {
			Database.close(conn);
		}
		log.info("DONE: Creating SYNTHEA_NATIVE database tables...");
	}

}
