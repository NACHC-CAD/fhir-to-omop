package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadMappingTables {

	public static void exec(File sqlFile, Connection conn) {
		File dir = new File(sqlFile.getParent());
		File raceFile = new File(dir, "RaceAndEthnicityCDC-OMOP-MAPPING-Race.txt");
		File ethFile = new File(dir, "RaceAndEthnicityCDC-OMOP-MAPPING-Eth.txt");
		log.info("-----------------------");
		log.info("LOAD RACE/ETH SQL FILE: " + FileUtil.getCanonicalPath(sqlFile));
		log.info("-----------------------");
		InputStream IS = FileUtil.getInputStream(sqlFile);
		String sqlString = FileUtil.getAsString(sqlFile);
		sqlString = sqlString.replace("<RACE_FILE>", FileUtil.getCanonicalPath(raceFile));
		sqlString = sqlString.replace("<ETH_FILE>", FileUtil.getCanonicalPath(ethFile));
		log.info("SQL STRING: \n" + sqlString);
		String dbName = AppParams.getDatabaseName();
		log.info("Using: " + dbName);
		Database.update("use " + dbName, conn);
		log.info("Running script...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done running script.");
		log.info("Done creating database tables.");
	}

}
