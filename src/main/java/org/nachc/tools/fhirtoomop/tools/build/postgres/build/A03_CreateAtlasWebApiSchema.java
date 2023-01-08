package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A03_CreateAtlasWebApiSchema {

	private static final String FILE_PATH = "/postgres/build/A03_CreateAtlasWebApiSchema.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating Atlas webapi schema.");
//		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			log.info("getting sql script...");
			log.info("executing script...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating Atlas webapi schema.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		return sqlString;
	}

}
