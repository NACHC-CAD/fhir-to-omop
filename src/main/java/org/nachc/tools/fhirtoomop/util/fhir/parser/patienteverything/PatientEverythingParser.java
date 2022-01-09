package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;

import com.nach.core.util.json.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientEverythingParser {


	private String jsonString;
	
	private Bundle bundle;
	
	public PatientEverythingParser(String bundleJson) {
		// TODO: (JEG) Should probably do some validation here
		this.jsonString = bundleJson;
		this.bundle = JsonParser.parse(bundleJson, Bundle.class);
	}
	
	public List<String> getResourceTypes() {
		List<String> types = new ArrayList<String>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for(BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			String type = resource.getClass().getName();
			types.add(type);
		}
		return types;
	}
	
}
