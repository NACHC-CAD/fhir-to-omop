package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddConstraints {

	private static final String PK = "/sqlserver/omop/OMOPCDM_sql_server_5.4_primary_keys.sql";
	
	private static final String CONSTRAINTS = "/sqlserver/omop/OMOPCDM_sql_server_5.4_constraints.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			String sqlString;
			String databaseName = AppParams.getDbName();
			// use
			Database.update("use " + databaseName, conn);
			// add primary keys
			logMsg("ADDING PRIMARY KEYS");
			sqlString = FileUtil.getAsString(PK);
			Database.executeSqlScript(sqlString, conn);
			// add constraints
			logMsg("ADDING CONSTRAINTS");
			sqlString = FileUtil.getAsString(CONSTRAINTS);
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
