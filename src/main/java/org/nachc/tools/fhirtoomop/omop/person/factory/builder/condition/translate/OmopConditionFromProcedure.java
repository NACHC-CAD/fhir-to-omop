package org.nachc.tools.fhirtoomop.omop.person.factory.builder.condition.translate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.procedure.ProcedureParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class OmopConditionFromProcedure {

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

	public OmopConditionFromProcedure(FhirPatient fhirPatient, ProcedureParser parser, ConceptDvo conceptDvo, OmopPerson omopPerson, Connection conn) {
		this.fhirPatient = fhirPatient;
		this.parser = parser;
		this.conceptDvo = conceptDvo;
		this.omopPerson = omopPerson;
		this.conn = conn;
	}

	public ConditionOccurrenceDvo build() {
		ConditionOccurrenceDvo dvo = new ConditionOccurrenceDvo();
		int measurementId = FhirToOmopIdGenerator.getId("measurement", "measurement_id");
		dvo.setConditionOccurrenceId(measurementId);
		dvo.setPersonId(omopPerson.getPersonId());
		dvo.setConditionConceptId(conceptDvo.getConceptId());
		dvo.setConditionStartDate(parser.getStartDate());
		dvo.setConditionEndDate(parser.getEndDate());
		dvo.setConditionTypeConceptId(0);
		String fhirEncounterId = parser.getEncounterId();
		Integer omopVisitId = this.omopPerson.getOmopEncounterId(fhirEncounterId);
		dvo.setVisitOccurrenceId(omopVisitId);
		return dvo;
	}

}
