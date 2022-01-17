package org.nachc.tools.fhirtoomop.tools.write;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.yaorma.dao.Dao;

public class WriteFhirPatientToOmop {

	public static void exec(String patientEverythingJson, Connection conn) {
		PatientEverythingParser parser = new PatientEverythingParser(patientEverythingJson);
		exec(parser, conn);
	}

	public static void exec(PatientEverythingParser patientEverythingParser, Connection conn) {
		OmopPersonEverythingFactory personEverything = new OmopPersonEverythingFactory(patientEverythingParser, conn);
		writePatient(personEverything, conn);
	}

	private static void writePatient(OmopPersonEverythingFactory personEverything, Connection conn) {
		PersonDvo dvo = personEverything.getPerson();
		Dao.insert(dvo, conn);
	}

}
