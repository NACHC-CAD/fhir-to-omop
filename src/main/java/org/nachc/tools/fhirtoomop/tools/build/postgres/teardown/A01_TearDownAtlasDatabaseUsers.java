package org.nachc.tools.fhirtoomop.tools.build.postgres.teardown;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class deletes the database users for the PostgreSQL database used by Atlas/WebAPI.  
 *
 */

@Slf4j
public class A01_TearDownAtlasDatabaseUsers {

	private static final String FILE_PATH = "/postgres/teardown/A01_TearDownAtlasDatabaseUsers.sql";

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
		String sqlString = getDropUsersSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done with init postgres users for Atlas.");
	}
	
	private static String getDropUsersSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		sqlString = sqlString.replace("<ohdsiAppUserUid>", AppParams.get("ohdsiAppUserUid"));
		return sqlString;
	}
	
}
