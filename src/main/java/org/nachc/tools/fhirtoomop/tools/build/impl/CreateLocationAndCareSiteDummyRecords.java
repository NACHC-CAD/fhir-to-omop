package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateLocationAndCareSiteDummyRecords {

	private static final String PATH = "/sqlserver/omop/5.4/location/create-location-and-caresite-dummy-records.sql";

	public static void exec(Connection conn) {
		String sqlString = FileUtil.getAsString(PATH);
		log.info("SQLSTRING: \n\n" + sqlString);
		Database.executeSqlScript(sqlString, conn);
	}

}
