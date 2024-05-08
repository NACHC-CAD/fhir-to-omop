package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabaseIndexes {

	private static final InputStream IS = FileUtil.getInputStream("/sqlserver/omop/5.3/OMOPCDM_sql_server_5.3_indices.sql");

	public static void exec(Connection conn) {
		String dbName = AppParams.getSchemaName();
		log.info("Using: " + dbName);
		Database.update("use " + dbName, conn);
		log.info("Running script...");
		String sqlString = updateSql(IS);
		Database.executeSqlScript(sqlString, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");		
	}
	
	private static String updateSql(InputStream is) {
		String str = FileUtil.getAsString(is);
		str = str.replaceAll("@cdmDatabaseSchema.", "");
		return str;
	}

}
