package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnableConstraints {

	public static void exec(Connection conn) {
		String sqlString = "EXEC sp_msforeachtable \"ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all\"";
		log.info("Enabling constraints\n:" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done with drop.");
	}
	
}
