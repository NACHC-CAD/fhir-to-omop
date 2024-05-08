package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunAchilles {

	public static final String FILE_NAME = "/sqlserver/omop/achilles/sql/run-achilles.sql";

	public static void exec(Connection conn) {
		log.info("Getting sql script to run Achilles...");
		String sqlString = FileUtil.getAsString(FILE_NAME);
		String cdmSchema = AppParams.getFullySpecifiedSchemaName();
		String resSchema = AppParams.getFullySpecifiedAchilliesResultsSchemaName();
		sqlString = sqlString.replaceAll("@FullySpecifiedCdmSchema", cdmSchema);
		sqlString = sqlString.replaceAll("@FullySpecifiedAchillesResultsSchema", resSchema);
		log.info("Running Achilles SQL script...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done executing Achilles sql script.");
	}

}
