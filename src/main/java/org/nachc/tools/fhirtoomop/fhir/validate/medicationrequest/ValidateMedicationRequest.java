package org.nachc.tools.fhirtoomop.fhir.validate.medicationrequest;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.nachc.tools.fhirtoomop.fhir.parser.medicationrequest.MedicationRequestParser;

public class ValidateMedicationRequest {

	private MedicationRequestParser med;
	
	private List<String> err = new ArrayList<String>();
	
	public static List<String> validate(List<MedicationRequestParser> list) {
		List<String> err = new ArrayList<String>();
		for(MedicationRequestParser med : list) {
			err.addAll(new ValidateMedicationRequest(med).validate().getErr());
		}
		return err;
	}
	
	public ValidateMedicationRequest(MedicationRequestParser med) {
		this.med = med;
	}
	
	public List<String> getErr() {
		return this.err;
	}
	
	public boolean isValid() {
		return err.size() == 0;
	}
	
	public ValidateMedicationRequest validate() {
		if(med.getMedicationRequestId() == null) {
			err.add("MedicationRequest: ID is null: " + med.getMedicationRequestId());
		}
		if(med.getPatientId() == null) {
			err.add("MedicationRequest: patientId is null: ");
		}
		if(med.getEncounterId() == null) {
			err.add("MedicationRequest: encounterId is null: " + med.getMedicationRequestId());
		}
		if(med.getStartDate() == null) {
			err.add("MedicationRequest: start date is null: " + med.getMedicationRequestId());
		}
		return this;
	}
	
}
