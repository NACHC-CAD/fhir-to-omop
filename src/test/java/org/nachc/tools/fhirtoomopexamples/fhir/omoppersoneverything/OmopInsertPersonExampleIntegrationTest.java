package org.nachc.tools.fhirtoomopexamples.fhir.omoppersoneverything;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttools.fhir.synthea.patient.GetASingleSyntheaPatient;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopInsertPersonExampleIntegrationTest {

	@Test 
	public void shouldInsertPatient() {
		log.info("Starting test...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			// the the json string for our test patient
			String json = GetASingleSyntheaPatient.getAsJson();
			log.info("Got json");
			// create the OmopPersonEverything object
			OmopPersonEverything person = new OmopPersonEverything(json, conn);
			log.info("Got person");
			// write the person to the database
			WriteFhirPatientToOmop.exec(person, conn);
			// commit the transaction
			Database.commit(conn);
			log.info("Done with commit");
			log.info("-------------");
			log.info("Your test patient has been added to the database:");
			log.info("FHIR ID: " + person.getPerson().getPersonSourceValue());
			log.info("OMOP ID: " + person.getPerson().getPersonId());
			log.info("-------------");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
