package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates the database users for the PostgreSQL database used by Atlas/WebAPI.  
 *
 */

@Slf4j
public class A01_CreateAtlasDatabaseUsers {

	private static final String FILE_PATH = "/postgres/build/A01_CreateAtlasDatabaseUsers.sql";

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		log.info("getting sql script...");
		log.info("executing script...");
		String sqlString = getCreateUsersScript();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done with init postgres users for Atlas.");
	}

	private static String getCreateUsersScript() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = replace(sqlString, "<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = replace(sqlString, "<ohdsiAdminUserPwd>", AppParams.get("ohdsiAdminUserPwd"));
		sqlString = replace(sqlString, "<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = replace(sqlString, "<ohdsiAdminPwd>", AppParams.get("ohdsiAdminPwd"));
		sqlString = replace(sqlString, "<ohdsiAppUserUid>", AppParams.get("ohdsiAppUserUid"));
		sqlString = replace(sqlString, "<ohdsiAppUserPwd>", AppParams.get("ohdsiAppUserPwd"));
		sqlString = replace(sqlString, "<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		sqlString = replace(sqlString, "<ohdsiAppPwd>", AppParams.get("ohdsiAppPwd"));
		return sqlString;
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}

}
