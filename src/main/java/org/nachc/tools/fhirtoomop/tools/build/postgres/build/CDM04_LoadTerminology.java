package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM04_LoadTerminology {

	private static final String SQL_STRING = FileUtil.getAsString("/postgres/build/CDM04_LoadTerminology.sql");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Loading terminologies...");
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			log.info("Done loading terminologies.");
			String rootDir = AppParams.getTerminologyRootDir();
			String sqlString = SQL_STRING.replace("@terminologiesRootFolder/", rootDir);
			InputStream is = new ByteArrayInputStream(sqlString.getBytes());
			log.info("Running script...");
			Database.executeSqlScript(is, conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		log.info("Done loading terminologies...");
	}

}
