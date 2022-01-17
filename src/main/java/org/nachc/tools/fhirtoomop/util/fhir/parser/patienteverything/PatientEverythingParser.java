package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Condition;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.util.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientEverythingParser {

	//
	// instance variables
	//

	private String jsonString;

	private Bundle fhirBundle;

	private BundleParser bundleParser;

	private PatientParser patientParser;

	//
	// constructor
	//

	public PatientEverythingParser(String bundleJson) {
		// TODO: (JEG) Should probably do some validation here
		this.jsonString = bundleJson;
		this.fhirBundle = FhirJsonParser.parse(bundleJson, Bundle.class);
		this.bundleParser = new BundleParser(bundleJson);
		this.patientParser = new PatientParser(bundleParser.getResourceForType(new Patient()));
	}

	// ---
	//
	// implementation
	//
	// ---

	//
	// trivial getters and setters
	//
	
	public PatientParser getPatient() {
		return this.patientParser;
	}

	//
	// get a list of the types found in this resource
	//
	
	public List<String> getResourceTypes() {
		return bundleParser.getResourceTypes();
	}

	public List<EncounterParser> getEncounterList() {
		List<EncounterParser> rtn = new ArrayList<EncounterParser>();
		List<Encounter> encounterList = this.bundleParser.getResourceListForType(new Encounter());
		for (Encounter enc : encounterList) {
			EncounterParser parser = new EncounterParser(enc);
			rtn.add(parser);
		}
		return rtn;
	}
	
	public List<ConditionParser> getConditionList() {
		List<ConditionParser> rtn = new ArrayList<ConditionParser>();
		List<Condition> conditionList = this.bundleParser.getResourceListForType(new Condition());
		for(Condition con : conditionList) {
			ConditionParser parser = new ConditionParser(con);
			rtn.add(parser);
		}
		return rtn;
	}

}
