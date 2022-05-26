package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
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
			sqlString = FileUtil.getAsString(DROP_PATH);
			log.info("doing drop...");
			Database.update(sqlString, conn);
			// create the database
			log.info("getting sql string for create...");
			sqlString = FileUtil.getAsString(CREATE_PATH);
			log.info("creating database...");
			Database.update(sqlString, conn);
			// database updates (privs etc)
			log.info("getting sql string for create...");
			sqlString = FileUtil.getAsString(UPDATE_PATH);
			log.info("doing updates for database...");
			Database.update(sqlString, conn);
			// done
			log.info("Done creating postgres database for Atlas.");
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}
	
}
