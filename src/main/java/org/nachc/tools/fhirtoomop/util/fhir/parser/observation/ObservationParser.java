package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

import java.math.BigDecimal;
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

	//
	// category
	//

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
		} catch (Exception exp) {
			return null;
		}
	}

	public String getCategorySystem() {
		try {
			return getCategory().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getCategoryDisplay() {
		try {
			return getCategory().getDisplay();
		} catch (Exception exp) {
			return null;
		}
	}

	public ObservationType getObservationType() {
		try {
			Coding coding = this.getCategory();
			if ("laboratory".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.LABORATORY;
			} else if ("vital-signs".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.VITAL_SIGNS;
			} else if ("survey".equalsIgnoreCase(coding.getCode())) {
				return ObservationType.SURVEY;
			} else {
				return ObservationType.OTHER;
			}
		} catch (Exception exp) {
			return ObservationType.OTHER;
		}
	}

	//
	// encounter
	//

	public String getEncounterId() {
		try {
			String ref = this.obs.getContext().getReference();
			if (ref.indexOf('/') < 0) {
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
		} catch (Exception exp) {
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
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// observation code (what observation is this)
	//

	public Coding getObservationCode() {
		try {
			return this.obs.getCode().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeSystem() {
		try {
			return this.obs.getCode().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeCode() {
		try {
			return this.obs.getCode().getCodingFirstRep().getCode();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeDisplay() {
		try {
			return this.obs.getCode().getCodingFirstRep().getDisplay();
		} catch (Exception exp) {
			return null;
		}
	}

	// -----------------------------------
	//
	// VALUES (There are several types of Observations)
	//
	// -----------------------------------

	//
	// value coding
	//

	public Coding getValueCoding() {
		try {
			return this.obs.getValueCodeableConcept().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingSystem() {
		try {
			return this.obs.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingCode() {
		try {
			return this.obs.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingDisplay() {
		try {
			return this.obs.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// value quantity
	//

	public Quantity getValueQuantity() {
		try {
			return obs.getValueQuantity();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueAsNumber() {
		try {
			Quantity q = getValueQuantity();
			BigDecimal bd = q.getValue();
			return bd.toString();
		} catch (Exception exp) {
			return null;
		}
	}

}
