package org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Condition;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.util.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.medicationrequest.MedicationRequestParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.enumerations.ObservationType;
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

	//
	// getters for lists of resources
	//

	public List<EncounterParser> getEncounterList() {
		List<EncounterParser> rtn = new ArrayList<EncounterParser>();
		List<Encounter> encounterList = this.bundleParser.getResourceListForType(new Encounter());
		for (Encounter enc : encounterList) {
			EncounterParser parser = new EncounterParser(enc);
			rtn.add(parser);
		}
		return rtn;
	}

	public EncounterParser getEncounter(String encounterId) {
		if(encounterId == null) {
			return null;
		} else {
			List<EncounterParser> encList = this.getEncounterList();
			for(EncounterParser enc : encList) {
				if(encounterId.equals(enc.getEncounterIdUnqualified())) {
					return enc;
				}
			}
			return null;
		}
	}
	
	public List<ConditionParser> getConditionList() {
		List<ConditionParser> rtn = new ArrayList<ConditionParser>();
		List<Condition> conditionList = this.bundleParser.getResourceListForType(new Condition());
		for (Condition con : conditionList) {
			ConditionParser parser = new ConditionParser(con);
			rtn.add(parser);
		}
		return rtn;
	}

	//
	// observation stuff
	//

	public List<ObservationParser> getObservationList() {
		return this.getObservationList(null);
	}

	public List<ObservationParser> getObservationList(ObservationType type) {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		List<Observation> observationList = this.bundleParser.getResourceListForType(new Observation());
		for (Observation obs : observationList) {
			ObservationParser parser = new ObservationParser(obs);
			if (type == null) {
				rtn.add(parser);
			} else {
				if (type == parser.getObservationType()) {
					rtn.add(parser);
				}
			}
		}
		return rtn;
	}

	public List<ObservationParser> getLabList() {
		return this.getObservationList(ObservationType.LABORATORY);
	}

	public List<ObservationParser> getVitalsList() {
		return this.getObservationList(ObservationType.VITAL_SIGNS);
	}

	public List<ObservationParser> getSurveyList() {
		return this.getObservationList(ObservationType.SURVEY);
	}

	//
	// medication request
	//

	public List<MedicationRequestParser> getMedicationRequestList() {
		List<MedicationRequestParser> rtn = new ArrayList<MedicationRequestParser>();
		List<MedicationRequest> medicationRequestList = this.bundleParser.getResourceListForType(new MedicationRequest());
		for(MedicationRequest medReq : medicationRequestList) {
			MedicationRequestParser parser = new MedicationRequestParser(medReq, this);
			rtn.add(parser);
		}
		return rtn;
	}

}
