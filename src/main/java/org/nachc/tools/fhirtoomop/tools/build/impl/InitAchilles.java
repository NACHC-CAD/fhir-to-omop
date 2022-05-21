package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitAchilles {

	private static final String PATH = "/sqlserver/omop/achilles/init-achilles.sql";

	public static void main(String[] args) {
		log.info("Starting main...");
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}
	
	public static void exec(Connection conn) {
		String databaseName = AppParams.getDbName();
		String vocabSchema = databaseName + ".dbo";
		String resultsSchema = databaseName + "_achilles_results" + ".dbo";
		String sqlString = FileUtil.getAsString(PATH);
		sqlString = sqlString.replace("<ACHILLES_RESULTS_SCHEMA>", resultsSchema);
		sqlString = sqlString.replace("<VOCAB_SCHEMA>", vocabSchema);
		String msg = "EXECUTING SQL SCRIPT:";
		msg += "\n-----------------------------------------------\n\n";
		msg += sqlString.trim();
		msg += "\n-----------------------------------------------";
		log.info(msg);
		Database.executeSqlScript(sqlString, conn);
		Database.commit(conn);
	}
	
}
