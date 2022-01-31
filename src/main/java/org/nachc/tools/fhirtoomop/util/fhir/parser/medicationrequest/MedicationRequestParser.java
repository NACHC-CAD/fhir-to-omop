package org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.MedicationRequest;

public class MedicationRequestParser {

	//
	// instance variables
	//
	
	private MedicationRequest medicationRequest;

	//
	// constructor
	//
	
	public MedicationRequestParser(MedicationRequest medicationRequest) {
		this.medicationRequest = medicationRequest;
	}

	//
	// medication
	//
	
	public Coding getMedication() {
		try {
			return this.medicationRequest.getMedicationCodeableConcept().getCoding().get(0);
		} catch (Exception exp) {
			return null;
		}
	}

	public String getMedicationCode() {
		try {
			return getMedication().getCode();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public String getMedicationSystem() {
		try {
			return getMedication().getSystem();
		} catch(Exception exp) {
			return null;
		}
		
	}
	
	public String getMedicationDisplay() {
		try {
			return getMedication().getDisplay();
		} catch(Exception exp) {
			return null;
		}
	}
	
}
