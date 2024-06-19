package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateMappingTables {

	private static final String FILE_NAME = "/sqlserver/terminology/mappings/race-eth/mapping-ddl.sql";
	
	public static void exec(Connection conn) {
		String dbName = AppParams.getDatabaseName();
		log.info("Using: " + dbName);
		Database.update("use " + dbName, conn);
		log.info("Running script...");
		InputStream is = FileUtil.getInputStream(FILE_NAME);
		Database.executeSqlScript(is, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");		
	}
	
}
