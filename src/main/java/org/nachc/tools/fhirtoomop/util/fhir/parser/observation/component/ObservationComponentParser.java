package org.nachc.tools.fhirtoomop.util.fhir.parser.observation.component;

import java.math.BigDecimal;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Quantity;
import org.hl7.fhir.dstu3.model.Observation.ObservationComponentComponent;

public class ObservationComponentParser {

	private ObservationComponentComponent comp;

	public ObservationComponentParser(ObservationComponentComponent comp) {
		this.comp = comp;
	}

	public Coding getCoding() {
		try {
			return this.comp.getCode().getCoding().get(0);
		} catch(Exception exp) {
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

}
