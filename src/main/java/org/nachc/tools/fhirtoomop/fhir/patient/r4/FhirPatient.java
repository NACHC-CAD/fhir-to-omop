package org.nachc.tools.fhirtoomop.fhir.patient.r4;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Resource;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.medicationrequest.MedicationRequestParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.observation.type.ObservationType;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.patient.PatientParser;
import org.nachc.tools.fhirtoomop.fhir.parser.r4.procedure.ProcedureParser;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class FhirPatient {

	private List<BundleParser> bundleParserList;

	private PatientParser patient;

	private List<String> resourceTypes = new ArrayList<String>();

	private List<EncounterParser> encounterList = new ArrayList<EncounterParser>();

	private List<ConditionParser> conditionList = new ArrayList<ConditionParser>();

	private List<MedicationRequestParser> medicationRequestList = new ArrayList<MedicationRequestParser>();

	private List<ObservationParser> observationList = new ArrayList<ObservationParser>();

	private List<ProcedureParser> procedureList = new ArrayList<ProcedureParser>();

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
		for (ObservationParser obs : this.observationList) {
			boolean isVitalSigns = obs.getObservationType() == ObservationType.VITAL_SIGNS;
			boolean isLab = obs.getObservationType() == ObservationType.LABORATORY;
			if (isVitalSigns == false && isLab == false) {
				rtn.add(obs);
			}
		}
		return rtn;
	}

	public List<ObservationParser> getMeasList() {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		for (ObservationParser obs : this.observationList) {
			boolean isVitalSigns = obs.getObservationType() == ObservationType.VITAL_SIGNS;
			boolean isLab = obs.getObservationType() == ObservationType.LABORATORY;
			if (isVitalSigns == true || isLab == true) {
				rtn.add(obs);
			}
		}
		return rtn;
	}

	private List<ObservationParser> getObservationListForType(ObservationType type) {
		List<ObservationParser> rtn = new ArrayList<ObservationParser>();
		for (ObservationParser obs : this.observationList) {
			if (obs.getObservationType() == type) {
				rtn.add(obs);
			}
		}
		return rtn;
	}

	/**
	 * Method to get the patientId
	 */
	public String getPatientId() {
		try {
			return this.patient.getId();
		} catch (Exception exp) {
			return null;
		}
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

	// ---
	//
	// get a list of resources for a given type.
	//
	// ---

	public <T extends IAnyResource> List<T> getResourceListForType(Class<T> cls) {
		List<T> rtn = new ArrayList<T>();
		for (BundleParser parser : this.bundleParserList) {
			getResourceListForType(cls, parser.getBundle(), rtn);
		}
		return rtn;
	}

	public <T extends IAnyResource> List<T> getResourceListForType(Class<T> cls, Bundle bundle, List<T> rtn) {
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			if (resource.getClass().equals(cls)) {
				rtn.add((T) resource);
			}
		}
		return rtn;
	}

	// ---
	//
	// get a list of resources for a given type.
	//
	// ---

	public <T extends IAnyResource> T getResourceForType(Class<T> cls, String id) {
		T rtn = null;
		for (BundleParser parser : this.bundleParserList) {
			rtn = getResourceForType(cls, id, parser.getBundle());
			if (rtn != null) {
				return rtn;
			}
		}
		return rtn;
	}

	public <T extends IAnyResource> T getResourceForType(Class<T> cls, String id, Bundle bundle) {
		if(id == null) {
			return null;
		}
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			String resourceId = resource.getId();
			log.info(id + "\t" + resourceId);
			if (resource.getClass().equals(cls) && resourceId != null && resourceId.contains(id)) {
				return (T) resource;
			}
		}
		return null;
	}

}
