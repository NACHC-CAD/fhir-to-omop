package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddConstraintsPostgres {

	private static final String PK = "/postgres/omop/cdm/OMOPCDM_postgresql_5.4_primary_keys.sql";
	
	private static final String CONSTRAINTS = "/postgres/omop/cdm/OMOPCDM_postgresql_5.4_constraints.sql";
	
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
		try {
			String sqlString;
			// add primary keys
			logMsg("ADDING PRIMARY KEYS");
			sqlString = FileUtil.getAsString(PK);
			sqlString = sqlString.replace("@cdmDatabaseSchema", "public");
			Database.executeSqlScript(sqlString, conn);
			// add constraints
			logMsg("ADDING CONSTRAINTS");
			sqlString = FileUtil.getAsString(CONSTRAINTS);
			sqlString = sqlString.replace("@cdmDatabaseSchema", "public");
			Database.executeSqlScript(sqlString, conn);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done adding constraints.");
	}

	private static void logMsg(String msg) {
		log.info("==================================================");
		log.info("* ");
		log.info("* " + msg);
		log.info("* ");
		log.info("==================================================");
	}
	
}
