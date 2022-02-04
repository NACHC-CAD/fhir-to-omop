package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class OmopObservationFactory {

	private OmopPersonEverythingFactory omopPersonEverything;
	
	private Connection conn;
	
	public OmopObservationFactory(OmopPersonEverythingFactory person, Connection conn) {
		this.omopPersonEverything = person;
		this.conn = conn;
	}
	/**
	 * 
	 * Get all observations for a given person.  
	 * 
	 */
	public List<ObservationDvo> getObservationList() {
		List<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		List<ObservationParser> obsList = this.omopPersonEverything.getFhirPatientEverything().getObservationList();
		for(ObservationParser obs : obsList) {
			List<ObservationDvo> dvoList = getObservationsForSingleFhirObservation(obs);
			rtn.addAll(dvoList);
		}
		return rtn;
	}

	//
	// is the fhir observation one or multiple observations
	//
	
	private boolean isMultipleObservations(ObservationParser obs) {
		return obs.isMultipart();
	}
	
	//
	// method to get omop observation(s) for fhir observation (fhir obs can have multiple omop obs, e.g. blood pressure)
	//
	
	public List<ObservationDvo> getObservationsForSingleFhirObservation(ObservationParser obs) {
		if(isMultipleObservations(obs)) {
			return getMultipleObservations(obs);
		} else {
			ArrayList<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
			rtn.add(getSingleObservation(obs));
			return rtn;
		}
	}
	
	//
	// method to get observations where multiple observations exist
	//
	
	private List<ObservationDvo> getMultipleObservations(ObservationParser obs) {
		List<ObservationComponentParser> comps = obs.getComponents();
		ArrayList<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		for(ObservationComponentParser comp : comps) {
			// get the values out of the comp
			ObservationDvo dvo = getSingleObservation(obs);
			// value as coding
			Coding valueCoding = comp.getValueCoding();
			ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
			Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
			dvo.setValueAsConceptId(valueConceptId);
			// value as number
			dvo.setValueAsNumber(comp.getValueAsNumber());
			rtn.add(dvo);
		}
		return rtn;
	}
	
	//
	// method to get a single observation
	//
	
	private ObservationDvo getSingleObservation(ObservationParser obs) {
		ObservationDvo dvo = new ObservationDvo();
		// observation id
		dvo.setObservationId(FhirToOmopIdGenerator.getId("observation", "observation_id", conn));
		// person
		Integer omopPatientId = this.omopPersonEverything.getOmopPatientId();
		dvo.setPersonId(omopPatientId);
		// observation concept id
		Coding obsCoding = obs.getObservationCode();
		ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
		Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
		dvo.setObservationConceptId(obsConceptId);
		dvo.setObservationSourceValue(obs.getId());
		// date
		dvo.setObservationDate(obs.getStartDate());
		// type
		dvo.setObservationTypeConceptId(0);
		// value as coding
		Coding valueCoding = obs.getValueCoding();
		ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
		Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
		dvo.setValueAsConceptId(valueConceptId);
		// value as number
		dvo.setValueAsNumber(obs.getValueAsNumber());
		return dvo;
	}

}
