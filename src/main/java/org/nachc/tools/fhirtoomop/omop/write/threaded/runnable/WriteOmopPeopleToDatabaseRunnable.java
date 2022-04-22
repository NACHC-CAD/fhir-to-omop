package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteOmopPersonToDatabase;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseRunnable implements Runnable {

	private List<String> fileList;

	private Connection conn;

	public WriteOmopPeopleToDatabaseRunnable(List<String> fileList, Connection conn) {
		this.fileList = fileList;
		this.conn = conn;
	}

	@Override
	public void run() {
		FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFromFileList();
		log.info("Done parsing file");
		OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
		WriteOmopPersonToDatabase.exec(omopPerson, conn);
		Database.commit(conn);
		log.info("DONE WRITING PATIENT TO DATABASE");		
	}

}
