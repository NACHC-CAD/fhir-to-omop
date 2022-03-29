package org.nachc.tools.fhirtoomop.omop.person;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * This is the class that turns a FHIR Patient/$everything resource into an OMOP
 * Person data value object (dvo).
 *
 */

@Getter
@Setter
public class OmopPerson {

	private PersonDvo person;

	private List<FhirPatient> patientEverythingList = new ArrayList<FhirPatient>();

	private List<VisitOccurrenceDvo> visitOccurrenceList = new ArrayList<VisitOccurrenceDvo>();

	private List<ConditionOccurrenceDvo> conditionOccurrenceList = new ArrayList<ConditionOccurrenceDvo>();

	private List<ObservationDvo> observationList = new ArrayList<ObservationDvo>();

	private List<ObservationDvo> measurementList = new ArrayList<ObservationDvo>();

	private List<DrugExposureDvo> drugExposureList = new ArrayList<DrugExposureDvo>();

	private List<ProcedureOccurrenceDvo> procedureOccurrenceList = new ArrayList<ProcedureOccurrenceDvo>();

}
