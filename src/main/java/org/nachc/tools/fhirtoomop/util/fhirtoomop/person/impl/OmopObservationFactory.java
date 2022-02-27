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
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationOrMeasurement;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationValueType;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.ConceptCache;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopObservationFactory {

	//
	// instance variables
	//
	
	private OmopPersonEverythingFactory omopPersonEverything;

	private Connection conn;

	//
	// constructor
	//
	
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
			log.debug("Getting...");
			List<ObservationDvoProxy> dvoList = getOmopObservationListForFhirObservation(obs);
			log.debug("GOT");
			rtn.addAll(dvoList);
		}
		return rtn;
	}

	//
	// implementation
	//
	
	private List<ObservationDvoProxy> getOmopObservationListForFhirObservation(ObservationParser obs) {
		List<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		if (isMultipleObservations(obs) == false) {
			// get for single observation
			rtn.add(getSingleObservation(obs));
		} else {
			// get for multi-part observation
			rtn = getMultipleObservations(obs);
		}
		return rtn;
	}

	private boolean isMultipleObservations(ObservationParser obs) {
		return obs.isMultipart();
	}

	// ---------------------------------
	//
	// SINGLE
	//
	// method to get a single observation
	//
	// ---------------------------------

	private ObservationDvoProxy getSingleObservation(ObservationParser parser) {
		ObservationDvo dvo = getBasicInformation(parser, true);
		// observation concept id
		Coding obsCoding = parser.getObservationCode();
		ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
		Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
		dvo.setObservationConceptId(obsConceptId);
		dvo.setObservationSourceValue(parser.getId());
		// value as coding
		Coding valueCoding = parser.getValueCoding();
		ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
		Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
		dvo.setValueAsConceptId(valueConceptId);
		// value as number
		dvo.setValueAsNumber(parser.getValueAsNumber());
		// value as string
		dvo.setValueAsString(parser.getValueAsString());
		// units
		String unitsSystem = parser.getUnitsCodingSystem();
		String unitsCode = parser.getUnitsCodingCode();
		ConceptDvo unitsConcept = getUnits(unitsSystem, unitsCode, conn);
		dvo.setUnitConceptId(unitsConcept.getConceptId());
		// type
		dvo.setObservationTypeConceptId(0);
		// create the proxy and return it
		ObservationDvoProxy proxy = new ObservationDvoProxy(dvo, parser, conn);
		return proxy;
	}
	
	private List<ObservationDvoProxy> getMultipleObservations(ObservationParser parser) {
		// create the return array and add the original concept as a parent concept
		ArrayList<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		ObservationDvoProxy parent = getSingleObservation(parser);
		rtn.add(parent);
		// process each component to create a new observation
		List<ObservationComponentParser> comps = parser.getComponents();
		for (ObservationComponentParser comp : comps) {
			// get the values out of the comp
			ObservationDvo dvo = getBasicInformation(parser, false);
			// observation concept id
			Coding obsCoding = comp.getObservationCode();
			ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
			Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
			dvo.setObservationConceptId(obsConceptId);
			dvo.setObservationSourceValue(parser.getId());
			// value as coding
			Coding valueCoding = comp.getValueCoding();
			ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
			Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
			dvo.setValueAsConceptId(valueConceptId);
			// value as number
			dvo.setValueAsNumber(comp.getValueAsNumber());
			// value as string
			dvo.setValueAsString(comp.getValueAsString());
			// units
			String unitsSystem = comp.getUnitsCodingSystem();
			String unitsCode = comp.getUnitsCodingCode();
			ConceptDvo unitsConcept = getUnits(unitsSystem, unitsCode, conn);
			dvo.setUnitConceptId(unitsConcept.getConceptId());
			// type
			dvo.setObservationTypeConceptId(0);
			// set the parent id
			dvo.setObservationEventId(parent.getObservationIdString());
			// create the proxy and add it to the return
			ObservationDvoProxy proxy = new ObservationDvoProxy(dvo, parser, conn);
			rtn.add(proxy);
		}
		return rtn;
	}

	private ObservationDvo getBasicInformation(ObservationParser parser, boolean isSingle) {
		ObservationDvo dvo = new ObservationDvo();
		// observation id
		dvo.setObservationId(FhirToOmopIdGenerator.getId("observation", "observation_id", conn));
		// person
		Integer omopPatientId = this.omopPersonEverything.getOmopPatientId();
		dvo.setPersonId(omopPatientId);
		// date
		dvo.setObservationDate(parser.getStartDate());
		return dvo;
	}
	
	private ConceptDvo getUnits(String unitsSystem, String unitsCode, Connection conn) {
		if (unitsSystem == null || unitsCode == null) {
			ConceptDvo unitsConceptDvo = new ConceptDvo();
			unitsConceptDvo.setConceptId(0);
			ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);			
			return unitsConceptDvo;
		} else {
			ConceptDvo unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, unitsCode, conn);
			// for some reason synthea uses curly brackets instead of brackets
			if (unitsConceptDvo == null) {
				String mod = unitsCode;
				mod = mod.replace("{", "[");
				mod = mod.replace("}", "]");
				unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, mod, conn);
				if (unitsConceptDvo != null) {
					ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
				}
			}
			// this is for concept_id 9117
			if (unitsConceptDvo == null) {
				String mod = unitsCode;
				mod = mod.replace("{", "");
				mod = mod.replace("}", "");
				mod = mod.replace('_', '.');
				unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, mod, conn);
				if (unitsConceptDvo != null) {
					ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
				}
			}
			// this is for mmHg
			if (unitsConceptDvo == null && unitsCode != null && "mmHg".equalsIgnoreCase(unitsCode)) {
				String mod = "mm[Hg]";
				unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, mod, conn);
				if (unitsConceptDvo != null) {
					ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
				}
			}
			ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
			if (unitsConceptDvo != null) {
				ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
			} else {
				unitsConceptDvo = new ConceptDvo();
				unitsConceptDvo.setConceptId(0);
				ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
			}
			return unitsConceptDvo;
		}
	}
	
}
