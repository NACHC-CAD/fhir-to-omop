package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Quantity;
import org.nachc.tools.fhirtoomop.util.fhir.general.FhirUtil;
import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.enumerations.ObservationType;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

public class ObservationParser {

	//
	// instance variables
	//

	private Observation obs;
	
	private PatientEverythingParser patient;

	//
	// constructor
	//

	public ObservationParser(Observation obs, PatientEverythingParser patient) {
		this.obs = obs;
		this.patient = patient;
	}

	public String getId() {
		return FhirUtil.getIdUnqualified(this.obs.getId());
	}

	public Coding getCategory() {
		try {
			// TODO: (JEG) Just getting first here (might need to be expanded for other use
			// cases)
			return this.obs.getCategory().get(0).getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}
	
	public String getCategoryCode() {
		try {
			return getCategory().getCode();
		} catch(Exception exp) {
			return null;
		}
	}

	public String getCategorySystem() {
		try {
			return getCategory().getSystem();
		} catch(Exception exp) {
			return null;
		}
	}

	public String getCategoryDisplay() {
		try {
			return getCategory().getDisplay();
		} catch(Exception exp) {
			return null;
		}
	}

	public Coding getCode() {
		try {
			return this.obs.getCode().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public Coding getValueCoding() {
		try {
			return this.obs.getValueCodeableConcept().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public Quantity getValueQuantity() {
		try {
			return obs.getValueQuantity();
		} catch (Exception exp) {
			return null;
		}
	}

	public ObservationType getObservationType() {
		try {
			Coding coding = this.getCategory();
			if("laboratory".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.LABORATORY;
			} else if("vital-signs".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.VITAL_SIGNS;
			} else if("survey".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.SURVEY;
			} else {
				return ObservationType.OTHER;
			}
		} catch(Exception exp) {
			return ObservationType.OTHER;
		}
	}

	//
	// encounter
	//
	
	public String getEncounterId() {
		try {
			String ref = this.obs.getContext().getReference();
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
