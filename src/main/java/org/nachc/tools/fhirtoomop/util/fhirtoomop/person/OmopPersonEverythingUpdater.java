package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopConditionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopDrugExposureFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopObservationFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopVisitOccurrenceFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationDvoProxy;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingUpdater {

	public static void addPages(OmopPersonEverything person, List<String> pageFileNames, Connection conn) {
		log.info("Adding page");
		// get the data from the file and create the fhir parser
		List<PatientEverythingParser> fhirPatientList = person.getFhirPatientEverythingList();
		for (String pageFileName : pageFileNames) {
			String json = FileUtil.getAsString(pageFileName);
			PatientEverythingParser fhirPatient = new PatientEverythingParser(json);
			log.info("Got patient from: " + pageFileName);
			fhirPatientList.add(fhirPatient);
		}
		for(PatientEverythingParser fhirPatient : fhirPatientList) {
			addVisits(person, fhirPatient, conn);
		}
		for(PatientEverythingParser fhirPatient : fhirPatientList) {
			addConditions(person, fhirPatient, conn);
		}
		for(PatientEverythingParser fhirPatient : fhirPatientList) {
			addDrugs(person, fhirPatient, conn);
		}
		for(PatientEverythingParser fhirPatient : fhirPatientList) {
			addObs(person, fhirPatient, conn);
		}
		log.info("Done adding pages.");
	}

	private static void addVisits(OmopPersonEverything person, PatientEverythingParser fhirPatient, Connection conn) {
		OmopVisitOccurrenceFactory factory = new OmopVisitOccurrenceFactory(person, conn);
		List<VisitOccurrenceDvo> list = factory.getVisitOccurencesList(fhirPatient);
		log.info("Adding " + list.size() + " visits");
		person.getVisitOccurrenceList().addAll(list);
	}

	private static void addConditions(OmopPersonEverything person, PatientEverythingParser fhirPatient, Connection conn) {
		OmopConditionFactory factory = new OmopConditionFactory(person, conn);
		List<ConditionOccurrenceDvo> list = factory.getConditionList(fhirPatient);
		log.info("Adding " + list.size() + " conditions");
		person.getConditionOccurrenceList().addAll(list);
	}

	private static void addDrugs(OmopPersonEverything person, PatientEverythingParser fhirPatient, Connection conn) {
		OmopDrugExposureFactory factory = new OmopDrugExposureFactory(person, conn);
		List<DrugExposureDvo> list = factory.getDrugExposureList(fhirPatient);
		log.info("Adding " + list.size() + " drugs");
		person.getDrugExposureList().addAll(list);
	}

	private static void addObs(OmopPersonEverything person, PatientEverythingParser fhirPatient, Connection conn) {
		OmopObservationFactory factory = new OmopObservationFactory(person, conn);
		List<ObservationDvoProxy> list = factory.getObservationList(fhirPatient);
		log.info("Adding " + list.size() + " drugs");
		person.getObservationProxyList().addAll(list);
	}

}
