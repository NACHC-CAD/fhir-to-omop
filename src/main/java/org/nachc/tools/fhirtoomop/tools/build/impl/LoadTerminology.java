package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadTerminology {

	private static final File FILE = FileUtil.getFile("/sqlserver/omop/load-terminology.sql");
	
	public static void exec(Connection conn) {
		String dbName = AppParams.getDbName();
		log.info("Using: " + dbName);
		Database.update("use " + dbName, conn);
		log.info("Running script...");
		Database.executeSqlScript(FILE, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");		
	}
	
}
