package org.nachc.tools.fhirtoomop.util.fhirtoomop.patient;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class FhirToOmopPersonParser {

	public PersonDvo getPerson(PatientEverythingParser patient) {
		PersonDvo dvo = new PersonDvo();
		String patientId = patient.getPatient().getId();
		// TODO: (JEG) add id generator class 
		dvo.setPersonSourceValue(patientId);
		return null;
	}

}
