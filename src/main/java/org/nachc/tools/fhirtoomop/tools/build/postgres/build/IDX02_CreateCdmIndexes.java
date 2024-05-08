package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IDX02_CreateCdmIndexes {

	private static final String SQL = FileUtil.getAsString("/postgres/build/omop/cdm/OMOPCDM_postgresql_5.4_indices.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating CDM INDEXES...");
		Timer timer = new Timer();
		timer.start();
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		log.info("Got connection...");
		try {
			log.info("Running script...");
			String dbName = AppParams.getSchemaName();
			log.info("DB NAME: " + dbName);
			String sqlString = SQL;
			sqlString = sqlString.replace("@cdmDatabaseSchema", dbName);
			log.info("Running script:\n\n" + sqlString + "\n\n");
			Database.executeSqlScript(sqlString, conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		log.info("TIME TO CREATE INDEXES: " + timer.getElapsedString());
		log.info("Done creating CDM INDEXES...");
	}

}
