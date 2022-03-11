package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadTerminology {

	private static final String SQL_STRING = FileUtil.getAsString("/sqlserver/omop/load-terminology.sql");

	public static void exec(Connection conn) {
		String rootDir = AppParams.getTerminologyRootDir();
		String sqlString = SQL_STRING.replace("@terminologiesRootFolder/", rootDir);
		InputStream is = new ByteArrayInputStream(sqlString.getBytes());
		String dbName = AppParams.getDbName();
		log.info("Using: " + dbName);
		Database.update("use " + dbName, conn);
		log.info("Running script...");
		Database.executeSqlScript(is, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");
	}

}
