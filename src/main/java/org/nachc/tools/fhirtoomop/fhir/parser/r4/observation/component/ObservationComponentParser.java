package org.nachc.tools.fhirtoomop.fhir.parser.r4.observation.component;

import java.math.BigDecimal;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Quantity;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.observation.ObservationParser;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;

public class ObservationComponentParser {

	private ObservationComponentComponent comp;
	
	private ObservationParser obs;

	public ObservationComponentParser(ObservationComponentComponent comp, ObservationParser obs) {
		this.comp = comp;
		this.obs = obs;
	}

	//
	// observation code (what observation is this)
	//

	public Coding getObservationCode() {
		try {
			return this.comp.getCode().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeSystem() {
		try {
			return this.comp.getCode().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeCode() {
		try {
			return this.comp.getCode().getCodingFirstRep().getCode();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationCodeDisplay() {
		try {
			return this.comp.getCode().getCodingFirstRep().getDisplay();
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
			return this.comp.getValueCodeableConcept().getCodingFirstRep();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingSystem() {
		try {
			return this.comp.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingCode() {
		try {
			return this.comp.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getValueCodingDisplay() {
		try {
			return this.comp.getValueCodeableConcept().getCodingFirstRep().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// operator
	//
	
	public String getOperator() {
		try {
			return comp.getValueQuantity().getComparator().getDisplay();
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
			return comp.getValueQuantity().getSystem();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsCodingCode() {
		try {
			return comp.getValueQuantity().getCode();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsCodingDisplay() {
		try {
			return comp.getValueQuantity().getUnit();
		} catch (Exception exp) {
			return null;
		}
	}

	//
	// value quantity
	//

	public Quantity getValueQuantity() {
		try {
			return comp.getValueQuantity();
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
	
	//
	// value string
	//
	
	public String getValueAsString() {
		try {
			return comp.getValueStringType().getValue();
		} catch(Exception exp) {
			return null;
		}
	}

}
