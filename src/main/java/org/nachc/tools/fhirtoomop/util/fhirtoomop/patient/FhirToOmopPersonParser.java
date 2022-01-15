package org.nachc.tools.fhirtoomop.util.fhirtoomop.patient;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class FhirToOmopPersonParser {
	
	public static PersonDvo getPerson(String json) {
		PatientEverythingParser patient = new PatientEverythingParser(json);
		return getPerson(patient);
	}
	
	public static PersonDvo getPerson(PatientEverythingParser patient) {
		PersonDvo dvo = new PersonDvo();
		// person_id
		Integer personId = FhirToOmopIdGenerator.getId("person", "person_id");
		dvo.setPersonId(personId);
		// person_source_value
		String patientId = patient.getPatient().getId();
		dvo.setPersonSourceValue(patientId);
		return dvo;
	}

}
