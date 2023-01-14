package org.nachc.tools.fhirtoomop.tools.build.postgres;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.populate.PopulateOmopInstanceFromFhirFiles;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateWithTestDataFromFhir {

	public static void main(String[] args) {
		log.info("Starting main...");
		log.info("Getting connections...");
		int maxConns = AppParams.getMaxNumberOfConnectionsForUpload();
		List<Connection> connectionList = new ArrayList<Connection>();
		for (int i = 0; i < maxConns; i++) {
			connectionList.add(PostgresDatabaseConnectionFactory.getCdmConnection());
		}
		log.info("Populating data...");
		new PopulateOmopInstanceFromFhirFiles(connectionList).exec();
		log.info("Done.");
	}
	
}
