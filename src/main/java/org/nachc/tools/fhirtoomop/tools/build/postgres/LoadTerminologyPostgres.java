package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadTerminologyPostgres {

	private static final String SQL_STRING = FileUtil.getAsString("/postgres/athena/load-terminology.sql");

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done loading terminologies.");
	}

	public static void exec(Connection conn) {
		String rootDir = AppParams.getTerminologyRootDir();
		String sqlString = SQL_STRING.replace("@terminologiesRootFolder/", rootDir);
		InputStream is = new ByteArrayInputStream(sqlString.getBytes());
		log.info("Running script...");
		Database.executeSqlScript(is, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");
	}

}
