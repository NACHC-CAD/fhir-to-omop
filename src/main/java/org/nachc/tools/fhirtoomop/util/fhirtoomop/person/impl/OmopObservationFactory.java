package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationDvoProxy;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationValueType;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public List<ObservationDvoProxy> getObservationList() {
		List<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		List<ObservationParser> obsList = this.omopPersonEverything.getFhirPatientEverything().getObservationList();
		for (ObservationParser obs : obsList) {
			List<ObservationDvoProxy> dvoList = getObservationsForSingleFhirObservation(obs);
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
	// method to get omop observation(s) for fhir observation (fhir obs can have
	// multiple omop obs, e.g. blood pressure)
	//

	public List<ObservationDvoProxy> getObservationsForSingleFhirObservation(ObservationParser obs) {
		if (isMultipleObservations(obs)) {
			return getMultipleObservations(obs);
		} else {
			ArrayList<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
			rtn.add(getSingleObservation(obs));
			return rtn;
		}
	}

	//
	// method to get observations where multiple observations exist
	//

	private List<ObservationDvoProxy> getMultipleObservations(ObservationParser obs) {
		List<ObservationComponentParser> comps = obs.getComponents();
		ArrayList<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		for (ObservationComponentParser comp : comps) {
			// get the values out of the comp
			ObservationDvoProxy proxy = getSingleObservation(obs, false);
			ObservationDvo dvo = proxy.getDvo();
			// observation concept id
			Coding obsCoding = comp.getObservationCode();
			ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
			Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
			dvo.setObservationConceptId(obsConceptId);
			dvo.setObservationSourceValue(obs.getId());
			// value as coding
			Coding valueCoding = comp.getValueCoding();
			ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
			Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
			dvo.setValueAsConceptId(valueConceptId);
			// value as number
			dvo.setValueAsNumber(comp.getValueAsNumber());
			rtn.add(new ObservationDvoProxy(dvo, conn));
			if (dvo.getObservationConceptId() == 0) {
				String display = comp.getObservationCodeDisplay();
				log.warn("COULD NOT MAP OBSERVATION TO CONCEPT: " + display);
			}
		}
		return rtn;
	}

	//
	// method to get a single observation
	//

	private ObservationDvoProxy getSingleObservation(ObservationParser obs) {
		return getSingleObservation(obs, true);
	}

	private ObservationDvoProxy getSingleObservation(ObservationParser parser, boolean isSingle) {
		ObservationDvo dvo = new ObservationDvo();
		// observation id
		dvo.setObservationId(FhirToOmopIdGenerator.getId("observation", "observation_id", conn));
		// person
		Integer omopPatientId = this.omopPersonEverything.getOmopPatientId();
		dvo.setPersonId(omopPatientId);
		// observation concept id
		Coding obsCoding = parser.getObservationCode();
		ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
		Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
		dvo.setObservationConceptId(obsConceptId);
		dvo.setObservationSourceValue(parser.getId());
		// date
		dvo.setObservationDate(parser.getStartDate());
		// type
		dvo.setObservationTypeConceptId(0);
		// value as coding
		Coding valueCoding = parser.getValueCoding();
		ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
		Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
		dvo.setValueAsConceptId(valueConceptId);
		// value as number
		dvo.setValueAsNumber(parser.getValueAsNumber());
		// get additional data for single observation (this is pulled multiple times for multivalue obs)
		ObservationDvoProxy proxy = new ObservationDvoProxy(dvo, conn);
		if (isSingle == true) {
			// log a warning if we couldn't get what kind of observation this is
			if(dvo.getObservationConceptId() == 0) {
				String display = parser.getObservationCodeDisplay();
				log.warn("COULD NOT GET CONCEPT FOR OBSERVATION: " + display);
			}
			// set the type
			if(parser.getValueCoding() != null) {
				proxy.setObservationValueType(ObservationValueType.CODED);
			} else if(parser.getValueAsNumber() != null) {
				proxy.setObservationValueType(ObservationValueType.QUANTITY);
			} else {
				proxy.setObservationValueType(ObservationValueType.STRING);
			}
		}
		return proxy;
	}

}
