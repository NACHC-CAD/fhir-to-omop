package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.FhirToOmopPersonParser;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

/**
 * 
 * This is the class that turns a FHIR Patient/$everything resource into an OMOP
 * Person data value object (dvo).
 *
 */

public class FhirToOmopPersonEverythingParser {

	private String json;

	private Connection conn;

	public FhirToOmopPersonEverythingParser(String json, Connection conn) {
		this.json = json;
		this.conn = conn;
	}

	public PersonDvo getPerson() {
		PatientEverythingParser patient = new PatientEverythingParser(json);
		return FhirToOmopPersonParser.getPerson(patient, conn);
	}

}
