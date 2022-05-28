package org.nachc.tools.fhirtoomop.tools.build.atlas.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
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
			log.info("executing script...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
			log.info("Done creating webapi schema.");
			log.info("Done.");
		} finally {
			Database.close(conn);
		}
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		return sqlString;
	}

}
