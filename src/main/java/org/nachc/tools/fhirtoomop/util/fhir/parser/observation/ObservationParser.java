package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Quantity;
import org.nachc.tools.fhirtoomop.util.fhir.general.FhirUtil;

import com.ibm.icu.util.StringTokenizer;

public class ObservationParser {

	//
	// instance variables
	//

	private Observation obs;

	//
	// constructor
	//

	public ObservationParser(Observation obs) {
		this.obs = obs;
	}

	public String getId() {
		return FhirUtil.getIdUnqualified(this.obs.getId());
	}

	public String getEncounterId() {
		try {
			String str = this.obs.getContext().getReference();
			if (str.indexOf("/") > 0) {
				StringTokenizer tokenizer = new StringTokenizer(str, "/");
				String type = tokenizer.nextToken();
				String val = tokenizer.nextToken();
				if ("encounter".equalsIgnoreCase(type) == false) {
					return null;
				} else {
					return val;
				}
			}
			return str;
		} catch (Exception exp) {
			return null;
		}
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

}
