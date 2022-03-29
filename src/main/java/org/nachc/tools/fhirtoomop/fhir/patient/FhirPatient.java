package org.nachc.tools.fhirtoomop.fhir.patient;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.nachc.tools.fhirtoomop.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.parser.medicationrequest.MedicationRequestParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.type.ObservationType;
import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FhirPatient {

	private PatientParser patient;

	private List<String> resourceTypes = new ArrayList<String>();
	
	private List<EncounterParser> encounterList = new ArrayList<EncounterParser>();

	private List<ConditionParser> conditionList = new ArrayList<ConditionParser>();

	private List<MedicationRequestParser> medicationRequestList = new ArrayList<MedicationRequestParser>();
	
	private List<ObservationParser> observationList = new ArrayList<ObservationParser>();
	
	//
	// observation methods
	//
	
	public List<ObservationParser> getLabList() {
		return this.getObservationListForType(ObservationType.LABORATORY);
	}

	public List<ObservationParser> getVitalsList() {
		return this.getObservationListForType(ObservationType.VITAL_SIGNS);
	}

	public List<ObservationParser> getSurveyList() {
		return this.getObservationListForType(ObservationType.SURVEY);
	}
	
	public List<ObservationParser> getObsList() {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		for(ObservationParser obs : this.observationList) {
			boolean isVitalSigns = obs.getObservationType() == ObservationType.VITAL_SIGNS;
			boolean isLab = obs.getObservationType() == ObservationType.LABORATORY;
			if(isVitalSigns == false && isLab == false) {
				rtn.add(obs);
			}
		}
		return rtn;
	}
	
	public List<ObservationParser> getMeasList() {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		for(ObservationParser obs : this.observationList) {
			boolean isVitalSigns = obs.getObservationType() == ObservationType.VITAL_SIGNS;
			boolean isLab = obs.getObservationType() == ObservationType.LABORATORY;
			if(isVitalSigns == true || isLab == true) {
				rtn.add(obs);
			}
		}
		return rtn;
	}
	
	private List<ObservationParser> getObservationListForType(ObservationType type) {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		for(ObservationParser obs : this.observationList) {
			if(obs.getObservationType() == type) {
				rtn.add(obs);
			}
		}
		return rtn;
	}
	
	/**
	 * Method to get an encounter for a given id.  
	 */
	public EncounterParser getEncounter(String encounterId) {
		if (encounterId == null) {
			return null;
		}
		for (EncounterParser enc : encounterList) {
			if (encounterId.equals(enc.getEncounterId())) {
				return enc;
			}
		}
		return null;
	}

}
