package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Observation;
import org.nachc.tools.fhirtoomop.util.fhir.general.FhirUtil;

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

	//
	// id
	//

	public String getId() {
		return FhirUtil.getIdUnqualified(this.obs.getId());
	}

	public Coding getCategory() {
		try {
			// TODO: (JEG) Just getting first here (might need to be expanded for other use cases)
			return this.obs.getCategory().get(0).getCodingFirstRep();
		} catch(Exception exp) {
			return null;
		}
	}

}
