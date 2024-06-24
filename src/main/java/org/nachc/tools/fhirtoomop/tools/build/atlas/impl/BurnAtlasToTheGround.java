package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnAtlasToTheGround {

	private static final String DROP_DB = "/sqlserver/omop/atlas/init-atlas-postgres-drop-database.sql";
	
	private static final String DROP_OBJS = "/sqlserver/omop/atlas/burn-atlas-to-the-ground.sql";
	
	public static void main(String[] args) {
		log.info("Starting main...");
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
//		dropPosgresDbObjects();
		dropSqlServerDbObjects();
	}
	
	public static void dropPosgresDbObjects() {
		log.info("Burning Atlas to the ground...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String sqlString;
			// drop the database
			log.info("Dropping database...");
			sqlString = getDropDbSqlString();
			Database.update(sqlString, conn);
			// drop other objects
			log.info("Dropping other postgres objects...");
			sqlString = getDropObjsSqlString();
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done burning Atlas to the ground.");
	}

	public static void dropSqlServerDbObjects() {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String databaseName = AppParams.getFullySpecifiedCdmSchemaName();
			databaseName = AppParams.getDatabasePart(databaseName);
			Database.update("drop database if exists " + databaseName + "_ach_res", conn);
			Database.update("drop database if exists " + databaseName + "_ach_tmp", conn);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}

	private static String getDropDbSqlString() {
		String sqlString = FileUtil.getAsString(DROP_DB);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.get("ohdsiDbName"));
		return sqlString;
	}
	
	private static String getDropObjsSqlString() {
		String sqlString = FileUtil.getAsString(DROP_OBJS);
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		sqlString = sqlString.replace("<ohdsiAppUserUid>", AppParams.get("ohdsiAppUserUid"));
		return sqlString;
	}
	
}
