package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VOC99_LoadTerminology {

	private static final String FILE_PATH = "/postgres/build/VOC99_LoadTerminology.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Loading terminologies...");
		Timer timer = new Timer();
		timer.start();
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		log.info("Got connection...");
		try {
			log.info("Done loading terminologies.");
			String sqlString = getSqlString();
			InputStream is = new ByteArrayInputStream(sqlString.getBytes());
			log.info("Running script...");
			Database.executeSqlScript(is, conn);
			log.info("Done running script.");
		} finally {
			Database.close(conn);
		}
		timer.stop();
		log.info("TIME TO LOAD TERMINOLOGIES: " + timer.getElapsedString());
		log.info("Done loading terminologies...");
	}

	private static String getSqlString() {
		String rootDir = AppParams.getTerminologyRootDir();
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<dbName>", AppParams.getDbName());
		sqlString = sqlString.replace("@terminologiesRootFolder/", rootDir);		
		return sqlString;
	}

}
