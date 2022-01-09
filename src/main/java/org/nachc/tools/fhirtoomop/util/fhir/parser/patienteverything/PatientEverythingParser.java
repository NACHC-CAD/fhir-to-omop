package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.nachc.tools.fhirtoomop.util.fhir.parser.bundle.BundleParser;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;

import com.nach.core.util.json.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientEverythingParser {

	//
	// instance variables
	//

	private String jsonString;
	
	private Bundle fhirBundle;
	
	private BundleParser bundle;
	
	//
	// constructor
	//
	
	public PatientEverythingParser(String bundleJson) {
		// TODO: (JEG) Should probably do some validation here
		this.jsonString = bundleJson;
		this.fhirBundle = JsonParser.parse(bundleJson, Bundle.class);
		this.bundle = new BundleParser(bundleJson);
	}

	// ---
	//
	// implementation
	//
	// ---
	
	public List<String> getResourceTypes() {
		return bundle.getResourceTypes();
	}

	public Patient getPatient() {
		return this.bundle.getPatient();
	}

}
