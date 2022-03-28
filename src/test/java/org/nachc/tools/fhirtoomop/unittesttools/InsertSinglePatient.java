package org.nachc.tools.fhirtoomop.unittesttools;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;

public class InsertSinglePatient {

	public static void exec(Connection conn) {
		List<String> patient = TestParams.getTestPatientAsFileList();
		OmopPersonEverything person = OmopPersonEverythingFactory.makePerson(patient, conn);
		WriteFhirPatientToOmop.exec(person, conn);
	}

}
