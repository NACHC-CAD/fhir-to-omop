package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopVisitOccurrenceFactory;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingUpdater {

	public static void addPage(OmopPersonEverything person, String pageFileName, Connection conn) {
		log.info("Adding page");
		// get the data from the file and create the fhir parser
		String json = FileUtil.getAsString(pageFileName);
		PatientEverythingParser fhirPatient = new PatientEverythingParser(json);
		log.info("Got patient from: " + pageFileName);
		// add visits
		addVisits(person, fhirPatient, conn);
		log.info("Done adding page.");
	}

	private static void addVisits(OmopPersonEverything person, PatientEverythingParser fhirPatient, Connection conn) {
		OmopVisitOccurrenceFactory factory = new OmopVisitOccurrenceFactory(person, conn);
		List<VisitOccurrenceDvo> list = factory.getVisitOccurencesList(fhirPatient);
		log.info("Adding " + list.size() + " visits");
		person.getVisitOccurrenceList().addAll(list);
	}

	
	
}
