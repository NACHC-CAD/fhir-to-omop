package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.type.ObservationType;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation.translate.OmopMeasurementFromObservation;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopObservationBuilder {

	//
	// instance variables
	//

	private FhirPatient fhirPatient;

	private OmopPerson omopPerson;

	private Connection conn;

	private List<ObservationDvo> observationList = new ArrayList<ObservationDvo>();

	private List<OmopMeasurementFromObservation> measurementList = new ArrayList<OmopMeasurementFromObservation>();

	private List<ProcedureOccurrenceDvo> procedureList = new ArrayList<ProcedureOccurrenceDvo>();

	//
	// constructor
	//

	public OmopObservationBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.fhirPatient = fhirPatient;
		this.omopPerson = omopPerson;
		this.conn = conn;
	}

	public void build() {
		buildObservationLists();
		omopPerson.setObservationList(this.observationList);
		omopPerson.setMeasurementList(this.getMeasurementList());
	}

	private List<MeasurementDvo> getMeasurementList() {
		ArrayList<MeasurementDvo> rtn = new ArrayList<MeasurementDvo>();
		for (OmopMeasurementFromObservation meas : this.measurementList) {
			MeasurementDvo dvo = meas.buildMeasurement();
			rtn.add(dvo);
		}
		return rtn;
	}

	//
	// implementation
	//

	private void buildObservationLists() {
		List<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		List<ObservationParser> obsList = fhirPatient.getObservationList();
		for (ObservationParser obs : obsList) {
			log.debug("Getting...");
			buildOmopObservationListForFhirObservation(obs);
			log.debug("GOT");
		}
	}

	private void buildOmopObservationListForFhirObservation(ObservationParser obs) {
		List<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		if (isMultipleObservations(obs) == false) {
			// get for single observation
			buildSingleObservation(obs);
		} else {
			// get for multi-part observation
			buildMultipleObservations(obs);
		}
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

	private ObservationDvo buildSingleObservation(ObservationParser parser) {
		ObservationDvo dvo = getBasicInformation(parser, true);
		// observation concept id
		Coding obsCoding = parser.getObservationCode();
		ConceptDvo obsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
		Integer obsConceptId = obsConceptDvo == null ? 0 : obsConceptDvo.getConceptId();
		dvo.setObservationConceptId(obsConceptId);
		dvo.setObservationSourceValue(parser.getId());
		// value as coding
		Coding valueCoding = parser.getValueCoding();
		if (valueCoding != null) {
			ConceptDvo valueConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(valueCoding, conn);
			Integer valueConceptId = valueConceptDvo == null ? null : valueConceptDvo.getConceptId();
			if (valueConceptId != null) {
				dvo.setValueAsConceptId(valueConceptId);
				dvo.setValueAsString(valueConceptDvo.getConceptName());
			}
		}
		// value as number
		dvo.setValueAsNumber(parser.getValueAsNumber());
		// value as string
		dvo.setValueAsString(parser.getValueAsString());
		// units
		String unitsSystem = parser.getUnitsCodingSystem();
		String unitsCode = parser.getUnitsCodingCode();
		ConceptDvo unitsConcept = getUnits(unitsSystem, unitsCode, conn);
		dvo.setUnitConceptId(unitsConcept.getConceptId());
		// observation type id
		dvo.setObservationTypeConceptId(OmopConceptConstants.getObsIsFromEhrEncounterRecord());
		// visitId
		try {
			VisitOccurrenceDvo visit = this.omopPerson.getVisitOccurrenceByFhirId(parser.getEncounterId());
			dvo.setVisitOccurrenceId(visit.getVisitOccurrenceId());
		} catch (NullPointerException npe) {
			log.error("COULD NOT GET VISIT ID");
		}
		// add to appropriate list
		if (isMeasurement(parser)) {
			// add to measurement if meas
			fixMeas(parser, dvo);
			OmopMeasurementFromObservation translator = new OmopMeasurementFromObservation(parser, null, dvo, conn);
			this.measurementList.add(translator);
		} else {
			// add to observations if obs
			this.observationList.add(dvo);
		}
		return dvo;
	}

	private boolean isMeasurement(ObservationParser obs) {
		if (obs.getObservationType() == ObservationType.VITAL_SIGNS || obs.getObservationType() == ObservationType.LABORATORY) {
			return true;
		} else {
			return false;
		}
	}

	private void fixMeas(ObservationParser parser, ObservationDvo dvo) {
		addMeasType(parser, dvo);
		checkUnits(parser, dvo);
	}

	private void addMeasType(ObservationParser parser, ObservationDvo dvo) {
		if (parser.getObservationType() == ObservationType.LABORATORY) {
			dvo.setObservationTypeConceptId(OmopConceptConstants.getObsIsLabResultMeasurementConceptId());
		} else {
			dvo.setObservationTypeConceptId(OmopConceptConstants.getObsIsFromPhysicalExaminationConceptId());
		}
	}

	private void checkUnits(ObservationParser parser, ObservationDvo dvo) {
		if (dvo.getValueAsNumber() == null && dvo.getValueAsConceptId() == null && dvo.getValueAsConceptId() == null && dvo.getUnitConceptId() == 0) {
			dvo.setUnitConceptId(OmopConceptConstants.getIsScalarMeasurementUnitsConceptId());
		}
		if (dvo.getUnitConceptId() == 0) {
			dvo.setUnitConceptId(OmopConceptConstants.getIsScalarMeasurementUnitsConceptId());
		}
	}

	private void buildMultipleObservations(ObservationParser parser) {
		// create the return array and add the original concept as a parent concept
		ArrayList<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		ObservationDvo parent = buildSingleObservation(parser);
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
			dvo.setObservationTypeConceptId(OmopConceptConstants.getObsIsFromEhrEncounterRecord());
			// set the parent id
			dvo.setObservationEventId(parent.getObservationId() == null ? null : parent.getObservationId() + "");
			// create the proxy and add it to the return
			if (isMeasurement(parser)) {
				fixMeas(parser, dvo);
				VisitOccurrenceDvo visit = this.omopPerson.getVisitOccurrenceByFhirId(parser.getEncounterId());
				dvo.setVisitOccurrenceId(visit.getVisitOccurrenceId());
				OmopMeasurementFromObservation translator = new OmopMeasurementFromObservation(null, comp, dvo, conn);
				this.measurementList.add(translator);
			} else {
				this.observationList.add(dvo);
			}
		}
	}

	private ObservationDvo getBasicInformation(ObservationParser parser, boolean isSingle) {
		ObservationDvo dvo = new ObservationDvo();
		// observation id
		dvo.setObservationId(FhirToOmopIdGenerator.getId("observation", "observation_id", conn));
		// person
		Integer omopPatientId = this.omopPerson.getPerson().getPersonId();
		dvo.setPersonId(omopPatientId);
		// date
		dvo.setObservationDate(parser.getStartDate());
		dvo.setObservationDatetime(dvo.getObservationDate());
		if (dvo.getObservationDate() == null) {
			String encounterId = parser.getEncounterId();
			VisitOccurrenceDvo visitDvo = this.omopPerson.getVisitOccurrenceByFhirId(encounterId);
			dvo.setVisitOccurrenceId(visitDvo.getVisitOccurrenceId());
			dvo.setObservationDate(visitDvo.getVisitStartDate());
		}
		dvo.setUnitSourceValue(parser.getUnitsCodingDisplay());
		dvo.setQualifierSourceValue(parser.getOperator());
		return dvo;
	}

	private ConceptDvo getUnits(String unitsSystem, String unitsCode, Connection conn) {
		if (unitsSystem == null || unitsCode == null) {
			ConceptDvo unitsConceptDvo = new ConceptDvo();
			unitsConceptDvo.setConceptId(0);
			return unitsConceptDvo;
		} else {
			ConceptDvo unitsConceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(unitsSystem, unitsCode, conn);
			return unitsConceptDvo;
		}
	}

}
