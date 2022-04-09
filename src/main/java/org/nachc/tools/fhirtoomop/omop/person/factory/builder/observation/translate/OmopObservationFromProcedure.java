package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation.translate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.procedure.ProcedureParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class OmopObservationFromProcedure {

	//
	// instance variables
	//

	private FhirPatient fhirPatient;

	private ProcedureParser parser;

	private OmopPerson omopPerson;

	private Connection conn;

	private ConceptDvo conceptDvo;

	private List<ObservationDvo> observationList = new ArrayList<ObservationDvo>();

	private List<ObservationDvo> measurementObsList = new ArrayList<ObservationDvo>();

	private List<MeasurementDvo> measurementList = new ArrayList<MeasurementDvo>();

	//
	// constructor
	//

	public OmopObservationFromProcedure(FhirPatient fhirPatient, ProcedureParser parser, ConceptDvo conceptDvo, OmopPerson omopPerson, Connection conn) {
		this.fhirPatient = fhirPatient;
		this.parser = parser;
		this.conceptDvo = conceptDvo;
		this.omopPerson = omopPerson;
		this.conn = conn;
	}

	public ObservationDvo build() {
		ObservationDvo dvo = new ObservationDvo();
		int measurementId = FhirToOmopIdGenerator.getId("observation", "observation_id", conn);
		dvo.setObservationId(measurementId);
		dvo.setPersonId(omopPerson.getPersonId());
		dvo.setObservationConceptId(conceptDvo.getConceptId());
		dvo.setObservationDate(parser.getStartDate());
		dvo.setObservationTypeConceptId(OmopConceptConstants.getObsIsFromEhrEncounterRecord());
		dvo.setUnitConceptId(OmopConceptConstants.getIsScalarMeasurementUnitsConceptId());
		dvo.setObservationSourceConceptId(OmopConceptConstants.getObsIsFromEhrEncounterRecord());
		String fhirEncounterId = parser.getEncounterId();
		Integer omopVisitId = this.omopPerson.getOmopEncounterId(fhirEncounterId);
		dvo.setVisitOccurrenceId(omopVisitId);
		return dvo;
	}

}
