package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisableConstraints {

	public static void exec(Connection conn) {
		String sqlString = "EXEC sp_msforeachtable \"ALTER TABLE ? NOCHECK CONSTRAINT all\"";
		log.info("Dropping constraints\n:" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done with drop.");
	}
	
}
