package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAchillesDatabases {

	private static final String PATH = "/sqlserver/omop/achilles/init-achilles.sql";

	public static void main(String[] args) {
		log.info("Starting main...");
		exec();
		log.info("Done.");
	}

	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		BurnAtlasToTheGround.dropSqlServerDbObjects();
		createDatabase(AppParams.getAchillesResultsDatabase(), conn);
		createDatabase(AppParams.getAchillesTempDatabase(), conn);
		runInitScript(conn);
		Database.commit(conn);
		log.info("Done creating Atlas Dependencies");
	}

	private static void createDatabase(String databaseName, Connection conn) {
		log.info("Creating database: " + databaseName);
		Database.update("drop database if exists " + databaseName, conn);
		Database.update("create database " + databaseName, conn);
		String uid = AppParams.getUid();
		addPrivs(databaseName, uid, conn);
	}

	private static void addPrivs(String schemaName, String uid, Connection conn) {
		// switch to the database
		Database.update("use " + schemaName, conn);
		// create the user
		log.info("Creating user: " + uid);
		Database.update("create user " + uid + " for login " + uid + " with default_schema = " + schemaName, conn);
		// add the privs
		log.info("Adding privileges...");
		Database.update("exec sp_addrolemember N'db_accessadmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_backupoperator', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datareader', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datawriter', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_ddladmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_owner', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_securityadmin', N'" + uid + "'", conn);
		// do the special grant for bulk upload
		Database.update("use master", conn);
		Database.update("GRANT ADMINISTER BULK OPERATIONS TO " + uid, conn);
		Database.commit(conn);
		Database.update("use " + schemaName, conn);
	}

	private static void runInitScript(Connection conn) {
		String msg = "";
		msg += "\n\n\n";
		msg += "\n------------------------------------------------";
		msg += "\n* ";
		msg += "\n* INITIALIZING ACHILLES ";
		msg += "\n* ";
		msg += "\n------------------------------------------------";
		msg += "\n\n\n";
		log.info(msg);
		String vocabSchema = AppParams.getAchillesVocabSchemaName();
		String resultsSchema = AppParams.getFullySpecifiedAchillesResultsSchemaName();
		String sqlString = FileUtil.getAsString(PATH);
		sqlString = sqlString.replace("<ACHILLES_RESULTS_SCHEMA>", resultsSchema);
		sqlString = sqlString.replace("<VOCAB_SCHEMA>", vocabSchema);
		msg = "EXECUTING SQL SCRIPT:";
		msg += "\n-----------------------------------------------\n\n";
		msg += sqlString.trim();
		msg += "\n-----------------------------------------------";
		log.info(msg);
		Database.executeSqlScript(sqlString, conn);
		Database.commit(conn);
	}

}
