package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabaseTablesPostgres {

	private static final String SQL = FileUtil.getAsString("/postgres/omop/cdm/OMOPCDM_postgresql_5.4_ddl.sql");

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		log.info("Running script...");
		String dbName = AppParams.getDbName();
		log.info("DB NAME: " + dbName);
		String sqlString = SQL;
		sqlString = sqlString.replace("@cdmDatabaseSchema", "public");
		log.info("Running script:\n\n" + sqlString + "\n\n");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");
	}

}
