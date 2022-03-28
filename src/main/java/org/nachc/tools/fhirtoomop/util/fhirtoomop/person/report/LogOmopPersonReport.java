package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.report;

import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogOmopPersonReport {

	public static void log(OmopPersonEverything person) {
		logPerson(person);
		logVisits(person);
		logConditions(person);
		logDrugs(person);
		logMeasurements(person);
		logObservations(person);
		logProcs(person);
	}

	private static void logPerson(OmopPersonEverything person) {
		PersonDvo dvo = person.getPerson();
		log.info("FHIR ID: " + dvo.getPersonSourceValue());
		log.info("OMOP ID: " + dvo.getPersonId());
	}
	
	private static void logVisits(OmopPersonEverything person) {
		List<VisitOccurrenceDvo> list = person.getVisitOccurrenceList();
		log.info("Got " + list.size() + " Visits");
	}
	
	private static void logMeasurements(OmopPersonEverything person) {
		List<MeasurementDvo> list = person.getMeasurementList();
		log.info("Got " + list.size() + " Measurements");
	}
	
	private static void logObservations(OmopPersonEverything person) {
		List<ObservationDvo> list = person.getObservationList();
		log.info("Got " + list.size() + " Observations");
	}

	private static void logConditions(OmopPersonEverything person) {
		List<ConditionOccurrenceDvo> list = person.getConditionOccurrenceList();
		log.info("Got " + list.size() + " Conditions");
	}

	private static void logDrugs(OmopPersonEverything person) {
		List<DrugExposureDvo> list = person.getDrugExposureList();
		log.info("Got " + list.size() + " Drugs");
	}
	
	private static void logProcs(OmopPersonEverything person) {
		List<ProcedureOccurrenceDvo> list = person.getProcedureOccurrenceList();
		log.info("Got " + list.size() + " Procs.");
	}

}
