package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class FhirToOmopPersonParser {
	
	public static PersonDvo getPerson(String json) {
		PatientEverythingParser patient = new PatientEverythingParser(json);
		return getPerson(patient);
	}
	
	public static PersonDvo getPerson(PatientEverythingParser personEverything) {
		PersonDvo dvo = new PersonDvo();
		PatientParser patient = personEverything.getPatient();
		// person_id
		Integer personId = FhirToOmopIdGenerator.getId("person", "person_id");
		dvo.setPersonId(personId);
		// person_source_value
		dvo.setPersonSourceValue(patient.getId());
		return dvo;
	}

}


