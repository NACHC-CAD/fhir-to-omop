package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtlasInstallCreateDatabase {

	private static final String DROP_PATH = "/sqlserver/omop/atlas/init-atlas-postgres-drop-database.sql";

	private static final String CREATE_PATH = "/sqlserver/omop/atlas/init-atlas-postgres-create-database.sql";

	private static final String UPDATE_PATH = "/sqlserver/omop/atlas/init-atlas-postgres-create-database-updates.sql";

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String sqlString;
			// drop if exists
			log.info("getting sql string for drop...");
			sqlString = getDropSql();
			log.info("doing drop...");
			Database.update(sqlString, conn);
			// create the database
			log.info("getting sql string for create...");
			sqlString = getCreateSql();
			log.info("creating database...");
			Database.update(sqlString, conn);
			// database updates (privs etc)
			log.info("getting sql string for create...");
			sqlString = getUpdateSql();
			log.info("doing updates for database...");
			Database.update(sqlString, conn);
			// done
			log.info("Done creating postgres database for Atlas.");
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}
	
	private static String getDropSql() {
		String sqlString = FileUtil.getAsString(DROP_PATH);	
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.get("ohdsiDbName"));
		return sqlString;
	}
	
	private static String getCreateSql() {
		String sqlString = FileUtil.getAsString(CREATE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.get("ohdsiDbName"));
		sqlString = sqlString.replace("<ohdsiDbOwner>", AppParams.get("ohdsiDbOwner"));
		return sqlString;
	}
	
	private static String getUpdateSql() {
		String sqlString = FileUtil.getAsString(UPDATE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.get("ohdsiDbName"));
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		return sqlString;
	}
	
}
