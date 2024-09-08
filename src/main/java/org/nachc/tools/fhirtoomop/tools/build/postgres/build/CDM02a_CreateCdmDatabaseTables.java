package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM02a_CreateCdmDatabaseTables {

	private static final String SQL = FileUtil.getAsString("/postgres/build/omop/cdm/OMOPCDM_postgresql_5.4_ddl.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		log.info("Creating CDM database tables...");
		log.info("Got connection...");
		log.info("Running script...");
		String dbName = AppParams.getDatabaseName();
		log.info("DB NAME: " + dbName);
		String sqlString = SQL;
		sqlString = sqlString.replace("@cdmDatabaseSchema", dbName);
		log.info("Running script:\n\n" + sqlString + "\n\n");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done running script.");
		log.info("Done creating CDM database tables.");
	}

}
