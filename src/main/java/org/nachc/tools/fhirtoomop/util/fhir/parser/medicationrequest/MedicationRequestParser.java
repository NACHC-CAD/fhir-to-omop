package org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

public class MedicationRequestParser {

	//
	// instance variables
	//

	private PatientEverythingParser patient;
	
	private MedicationRequest medicationRequest;

	//
	// constructor
	//

	public MedicationRequestParser(MedicationRequest medicationRequest, PatientEverythingParser patient) {
		this.patient = patient;
		this.medicationRequest = medicationRequest;
	}

	//
	// trivial getters
	//

	public MedicationRequest getMedicationRequest() {
		return medicationRequest;
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
		} catch (Exception exp) {
			return null;
		}
	}

	public String getMedicationSystem() {
		try {
			return getMedication().getSystem();
		} catch (Exception exp) {
			return null;
		}

	}

	public String getMedicationDisplay() {
		try {
			return getMedication().getDisplay();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// status
	//

	public String getStatus() {
		try {
			return this.medicationRequest.getStatusElement().getValueAsString();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// intent
	//

	public String getIntent() {
		try {
			return this.medicationRequest.getIntentElement().getValueAsString();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// encounter
	//

	public String getEncounterId() {
		try {
			String ref = this.medicationRequest.getContext().getReference();
			if(ref.indexOf('/') < 0) {
				return ref;
			} else {
				return ref.split("/")[1];
			}
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// patient
	//
	
	public String getPatientId() {
		try {
			return this.patient.getPatient().getId();
		} catch(Exception exp) {
			return null;
		}
	}
	
	//
	// start date
	//
	
	public Date getStartDate() {
		try {
			String encounterId = this.getEncounterId();
			EncounterParser enc = this.patient.getEncounter(encounterId);
			return enc.getStartDate();
		} catch(Exception exp) {
			return null;
		}
	}
	
}
