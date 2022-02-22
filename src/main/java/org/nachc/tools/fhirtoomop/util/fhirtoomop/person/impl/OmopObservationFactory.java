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
			log.debug("Getting...");
			// JEG: THIS IS DEFINITELY THE PROBLEM
			List<ObservationDvoProxy> dvoList = getObservationsForSingleFhirObservation(obs);
			log.debug("GOT");
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
		List<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		if (isMultipleObservations(obs)) {
			rtn = getMultipleObservations(obs);
		} else {
			rtn.add(getSingleObservation(obs));
		}
		return rtn;
	}

	// ---------------------------------
	//
	// COMMON
	//
	// called by both get single and get multiple to get the values common to both
	//
	// ---------------------------------

	private ObservationDvo getSingleObservation(ObservationParser parser, boolean isSingle) {
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
		return dvo;
	}

	// ---------------------------------
	//
	// SINGLE
	//
	// method to get a single observation
	//
	// ---------------------------------

	private ObservationDvoProxy getSingleObservation(ObservationParser parser) {
		ObservationDvo dvo = getSingleObservation(parser, true);
		// log a warning if we couldn't get what kind of observation this is
		if (dvo != null && (dvo.getObservationConceptId() == null || dvo.getObservationConceptId() == 0)) {
			String display = parser.getObservationCodeDisplay();
			log.warn("COULD NOT GET CONCEPT FOR OBSERVATION: " + display);
		}
		// value as coding
		Coding valueCoding = parser.getValueCoding();
		ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
		Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
		dvo.setValueAsConceptId(valueConceptId);
		// value as number
		dvo.setValueAsNumber(parser.getValueAsNumber());
		// units
		String unitsSystem = parser.getUnitsCodingSystem();
		String unitsCode = parser.getUnitsCodingCode();
		if (unitsSystem != null && unitsCode != null) {
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
				dvo.setUnitConceptId(unitsConceptDvo.getConceptId());
				ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
			} else {
				ConceptDvo zeroConcept = new ConceptDvo();
				zeroConcept.setConceptId(0);
				ConceptCache.add(unitsSystem, unitsCode, zeroConcept);
			}
		}
		// CREATE THE PROXY (ALL CONCEPTS NEED TO BE SET BEFORE THIS IS CALLED)
		ObservationDvoProxy proxy = new ObservationDvoProxy(dvo, conn);
		// set the observation type
		proxy.setObservationType(parser.getObservationType());
		// set obs or meas
		switch (proxy.getObservationType()) {
		case LABORATORY:
			proxy.setObservationOrMeasurement(ObservationOrMeasurement.MEASUREMENT);
			break;
		case VITAL_SIGNS:
			proxy.setObservationOrMeasurement(ObservationOrMeasurement.MEASUREMENT);
			break;
		default:
			proxy.setObservationOrMeasurement(ObservationOrMeasurement.OBSERVATION);
			break;
		}
		// set the value type
		if (parser.getValueCoding() != null) {
			proxy.setObservationValueType(ObservationValueType.CODED);
		} else if (parser.getValueAsNumber() != null) {
			proxy.setObservationValueType(ObservationValueType.QUANTITY);
		} else {
			proxy.setObservationValueType(ObservationValueType.STRING);
		}
		// type
		dvo.setObservationTypeConceptId(0);
		// done
		return proxy;
	}

	// ---------------------------------
	//
	// MULITIPLE
	//
	// method to get observations where multiple observations exist
	//
	// ---------------------------------

	private List<ObservationDvoProxy> getMultipleObservations(ObservationParser obs) {
		List<ObservationComponentParser> comps = obs.getComponents();
		ArrayList<ObservationDvoProxy> rtn = new ArrayList<ObservationDvoProxy>();
		// process each component to create a new observation
		for (ObservationComponentParser comp : comps) {
			// get the values out of the comp
			ObservationDvo dvo = getSingleObservation(obs, false);
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
			// display warning if we couldn't get what kind of observation this is
			if (dvo.getObservationConceptId() == 0) {
				String display = comp.getObservationCodeDisplay();
				log.warn("COULD NOT MAP OBSERVATION TO CONCEPT: " + display);
			}
			// units
			String unitsSystem = comp.getUnitsCodingSystem();
			String unitsCode = comp.getUnitsCodingCode();
			if (unitsSystem != null && unitsCode != null) {
				ConceptDvo unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, unitsCode, conn);
				// for some reason synthea uses curly brackets instead of brackets
				if (unitsConceptDvo == null) {
					unitsCode = unitsCode.replace('{', '[');
					unitsCode = unitsCode.replace('}', ']');
					unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, unitsCode, conn);
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
				if (unitsConceptDvo != null) {
					dvo.setUnitConceptId(unitsConceptDvo.getConceptId());
					ConceptCache.add(unitsSystem, unitsCode, unitsConceptDvo);
				} else {
					ConceptDvo zeroConcept = new ConceptDvo();
					zeroConcept.setConceptId(0);
					ConceptCache.add(unitsSystem, unitsCode, zeroConcept);
				}
			}
			// CREATE THE PROXY (ALL CONCEPTS NEED TO BE SET BEFORE THIS IS CALLED)
			ObservationDvoProxy proxy = new ObservationDvoProxy(dvo, conn);
			// set the observation type
			proxy.setObservationType(obs.getObservationType());
			// set obs or meas
			switch (proxy.getObservationType()) {
			case LABORATORY:
				proxy.setObservationOrMeasurement(ObservationOrMeasurement.MEASUREMENT);
				break;
			case VITAL_SIGNS:
				proxy.setObservationOrMeasurement(ObservationOrMeasurement.MEASUREMENT);
				break;
			default:
				proxy.setObservationOrMeasurement(ObservationOrMeasurement.OBSERVATION);
				break;
			}
			// set the value type
			if (comp.getValueCoding() != null) {
				proxy.setObservationValueType(ObservationValueType.CODED);
			} else if (comp.getValueAsNumber() != null) {
				proxy.setObservationValueType(ObservationValueType.QUANTITY);
			} else {
				proxy.setObservationValueType(ObservationValueType.STRING);
			}
			// type
			dvo.setObservationTypeConceptId(0);
			// add the obs to the return collection
			rtn.add(proxy);
		}
		return rtn;
	}

}
