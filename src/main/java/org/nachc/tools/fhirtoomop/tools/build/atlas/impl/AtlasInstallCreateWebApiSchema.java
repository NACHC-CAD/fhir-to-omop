package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtlasInstallCreateWebApiSchema {

	private static final String FILE_PATH = "/sqlserver/omop/atlas/init-atlas-postgres-create-webapi-schema.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			log.info("getting sql script...");
			String sqlString = FileUtil.getAsString(FILE_PATH);
			log.info("executing script...");
			Database.executeSqlScript(sqlString, conn);
			log.info("Done creating webapi schema.");
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}}
