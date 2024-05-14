package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A07_GrantPrivileges {

	private static final String FILE_PATH = "/postgres/build/A07_GrantPrivileges.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating privileges.");
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			log.info("getting sql script...");
			log.info("executing script...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating privileges.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", AppParams.get("ohdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAdminUid>", AppParams.get("ohdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAppUserUid>", AppParams.get("ohdsiAppUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", AppParams.get("ohdsiAppUid"));
		sqlString = sqlString.replace("<public>", "public");
		return sqlString;
	}

}
