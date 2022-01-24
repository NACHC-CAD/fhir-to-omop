package org.nachc.tools.fhirtoomop.util.fhir.parser.observation;

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
	

	
}
