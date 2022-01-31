package org.nachc.tools.fhirtoomop.unittesttools;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

public class InsertSinglePatient {

	public static void exec(Connection conn) {
		PatientEverythingParser patient = TestParams.getPatientEverything();
		WriteFhirPatientToOmop.exec(patient, conn);
	}

}
