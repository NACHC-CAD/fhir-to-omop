package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurnAtlasToTheGround {

	private static final String DROP_DB = "/sqlserver/omop/atlas/init-atlas-postgres-drop-database.sql";
	
	private static final String DROP_OBJS = "/sqlserver/omop/atlas/burn-atlas-to-the-ground.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		log.info("Burning Atlas to the ground...");
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String sqlString;
			// drop the database
			log.info("Dropping database...");
			sqlString = FileUtil.getAsString(DROP_DB);
			Database.update(sqlString, conn);
			// drop other objects
			log.info("Dropping other postgres objects...");
			sqlString = FileUtil.getAsString(DROP_OBJS);
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done burning Atlas to the ground.");
	}
}
