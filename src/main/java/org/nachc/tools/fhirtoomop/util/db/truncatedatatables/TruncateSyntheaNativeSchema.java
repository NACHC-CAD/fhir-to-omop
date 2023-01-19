package org.nachc.tools.fhirtoomop.util.db.truncatedatatables;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateSyntheaNativeSchema {	

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			log.info("Dropping synthea_native schema...");
			String sqlString;
			sqlString = "drop schema if exists synthea_native cascade";
			Database.update(sqlString, conn);
			log.info("Creating empty synthea_native schema...");
			sqlString = "create schema synthea_native";
			Database.update(sqlString, conn);
		} finally {
			log.info("Closing database connection...");
			Database.close(conn);
		}
		log.info("Done dropping synthea_native schema.");
	}
	
}
