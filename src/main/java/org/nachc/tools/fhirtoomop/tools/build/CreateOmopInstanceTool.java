package org.nachc.tools.fhirtoomop.tools.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates an instance of OMOP from scratch based on the settings in
 * the app.properties file. You will also need to provide the following files
 * (see the readme.txt files in these folders for what needs to be in each)
 * 
 * /src/main/resources/auth/app-auth.properties
 * /src/main/resources/auth/synthea-auth.properties
 *
 */

@Slf4j
public class CreateOmopInstanceTool {

	public static void main(String[] args) {
		log.info("Creating OMOP instance...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Creating tables...");
		log.info("Done.");
	}
	
}
