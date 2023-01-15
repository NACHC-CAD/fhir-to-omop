package org.nachc.tools.fhirtoomop.fhir.parser.observation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Observation.ObservationComponentComponent;
import org.hl7.fhir.dstu3.model.Quantity;
import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.type.ObservationType;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.util.id.FhirUtil;

public class ObservationParser {

	//
	// instance variables
	//

	private Observation obs;

	private FhirPatient patient;

	//
	// constructor
	//

	public ObservationParser(Observation obs, FhirPatient patient) {
		this.obs = obs;
		this.patient = patient;
	}

	/**
	 * 
	 * A single FHIR observation can represent multiple measurements.
	 * 
	 */

	public boolean isMultipart() {
		if (this.getComponents().size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	//
	// component
	//

	public List<ObservationComponentParser> getComponents() {
		try {
			ArrayList<ObservationComponentParser> rtn = new ArrayList<ObservationComponentParser>();
			List<ObservationComponentComponent> componentList = this.obs.getComponent();
			for (ObservationComponentComponent comp : componentList) {
				ObservationComponentParser parser = new ObservationComponentParser(comp, this);
				rtn.add(parser);
			}
			return rtn;
		} catch (Exception exp) {
			return new ArrayList<ObservationComponentParser>();
		}
	}

	//
	// id
	//

	public String getId() {
		return FhirUtil.getIdUnqualified(this.obs.getId());
	}

	//
	// category
	//

	public Coding getCategory() {
		try {
			// TODO: (JEG) Just getting first here
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

	public Date getEndDate() {
		try {
			String encounterId = this.getEncounterId();
			EncounterParser enc = this.patient.getEncounter(encounterId);
			Date rtn = enc.getEndDate();
			if(rtn == null) {
				rtn = getStartDate();
			}
			return rtn;
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

	public BigDecimal getValueAsNumber() {
		try {
			Quantity q = getValueQuantity();
			BigDecimal bd = q.getValue();
			return bd;
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// value string
	//

	public String getValueAsString() {
		try {
			return obs.getValueStringType().getValue();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// operator
	//
	
	public String getOperator() {
		try {
			return obs.getValueQuantity().getComparator().getDisplay();
		} catch(Exception exp) {
			return null;
		}
	}
	
	// -----------------------------------
	//
	// UNITS (There are several types of Observations)
	//
	// -----------------------------------

	public String getUnitsCodingSystem() {
		try {
			return obs.getValueQuantity().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsCodingCode() {
		try {
			return obs.getValueQuantity().getCode();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsCodingDisplay() {
		try {
			return obs.getValueQuantity().getUnit();
		} catch (Exception exp) {
			return null;
		}
	}

}
