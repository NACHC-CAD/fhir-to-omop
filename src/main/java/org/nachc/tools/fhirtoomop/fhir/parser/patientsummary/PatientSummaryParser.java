package org.nachc.tools.fhirtoomop.fhir.parser.patientsummary;

import org.hl7.fhir.dstu3.model.Patient;

public class PatientSummaryParser {

	private Patient patient;

	public PatientSummaryParser(Patient patient) {
		this.patient = patient;
	}

	public String getId() {
		String patientId = patient.getIdElement().getIdPart();
		return patientId;
	}

}
