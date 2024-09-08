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
public class DropDatabaseUsersForPostgres {

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
		log.info("Doing teardown for Atlas database users...");
		log.info("getting sql script...");
		log.info("executing script...");
		String sqlString = getDropUsersSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done with teardown for Atlas database users.");
	}
	
	private static String getDropUsersSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.getUid());
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.getUid());
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("Uuid"));
		sqlString = sqlString.replace("<ohdsiAppUserUid>", AppParams.get("Uuid"));
		return sqlString;
	}
	
}
