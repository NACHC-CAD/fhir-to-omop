package org.nachc.tools.fhirtoomop;

import org.nachc.tools.fhirtoomop.tools.build.postgres.CreateOmopInstanceToolPostgres;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildTestEnvironment {

	public static void main(String[] args) {
		log.info("Building environment...");
		CreateOmopInstanceToolPostgres.exec();
		log.info("Done.");
	}

}
