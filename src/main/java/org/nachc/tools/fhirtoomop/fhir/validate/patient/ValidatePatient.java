package org.nachc.tools.fhirtoomop.fhir.validate.patient;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatePatient {

	private PatientParser pat;

	private List<String> err = new ArrayList<String>();

	public ValidatePatient(PatientParser pat) {
		this.pat = pat;
	}

	public List<String> getErr() {
		return this.err;
	}
	
	public ValidatePatient validate() {
		log.info("---");
		log.info("Patient");
		log.info("---");
		log.info("Patient: (id)   " + pat.getId());
		log.info("Patient: (bday) " + pat.getBirthDateAsString());
		log.info("Patient: (race) " + pat.getRaceStr());
		log.info("Patient: (eth)  " + pat.getEthnicityStr());
		if (pat.getId() == null) {
			err.add("Patient: id is null");
		}
		return this;
	}

}
