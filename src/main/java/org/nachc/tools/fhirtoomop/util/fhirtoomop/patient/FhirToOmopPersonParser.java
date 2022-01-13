package org.nachc.tools.fhirtoomop.util.fhirtoomop.patient;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

public class FhirToOmopPersonParser {

	public PersonDvo getPerson(PatientEverythingParser patient) {
		PersonDvo dvo = new PersonDvo();
		// person_id
		Integer personId = FhirToOmopIdGenerator.getId("person", "person_id");
		dvo.setPersonId(personId);
		// person_source_value
		String patientId = patient.getPatient().getId();
		dvo.setPersonSourceValue(patientId);
		return null;
	}

}
