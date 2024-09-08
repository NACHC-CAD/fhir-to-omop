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
		String sqlString = getSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done with init postgres users for Atlas.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = replace(sqlString, "<ohdsiAdminUserUid>", AppParams.getUid());
		sqlString = replace(sqlString, "<ohdsiAdminUserPwd>", AppParams.getPwd());
		sqlString = replace(sqlString, "<ohdsiAdminUid>", AppParams.getUid());
		sqlString = replace(sqlString, "<ohdsiAdminPwd>", AppParams.getUid());
		sqlString = replace(sqlString, "<ohdsiAppUserUid>", AppParams.get("Uuid"));
		sqlString = replace(sqlString, "<ohdsiAppUserPwd>", AppParams.get("Upwd"));
		sqlString = replace(sqlString, "<ohdsiAppUid>", AppParams.get("Uuid"));
		sqlString = replace(sqlString, "<ohdsiAppPwd>", AppParams.get("Upwd"));
		return sqlString;
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}

}
