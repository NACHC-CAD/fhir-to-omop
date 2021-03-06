package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteOmopPersonToDatabase;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseRunnable implements Runnable {

	private FhirPatientResources resources;

	private Connection conn;

	public WriteOmopPeopleToDatabaseRunnable(FhirPatientResources resources, Connection conn) {
		this.resources = resources;
		this.conn = conn;
	}

	@Override
	public void run() {
		try {
			FhirPatient fhirPatient = new FhirPatientFactory(resources).build();
			log.info("Done parsing file");
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			WriteOmopPersonToDatabase.exec(omopPerson, conn);
			Database.commit(conn);
			log.info("DONE WRITING PATIENT TO DATABASE");
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}

}
