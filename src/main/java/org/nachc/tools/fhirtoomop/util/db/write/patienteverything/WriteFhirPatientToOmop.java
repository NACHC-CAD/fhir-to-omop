package org.nachc.tools.fhirtoomop.util.db.write.patienteverything;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.FhirResourceDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

public class WriteFhirPatientToOmop {

	public static void exec(String patientEverythingJson, Connection conn) {
		PatientEverythingParser parser = new PatientEverythingParser(patientEverythingJson);
		exec(parser, conn);
	}

	public static void exec(PatientEverythingParser patientEverythingParser, Connection conn) {
		OmopPersonEverythingFactory personEverything = new OmopPersonEverythingFactory(patientEverythingParser, conn);
		writeFhirResources(personEverything, conn);
		writePatient(personEverything, conn);
		writeVisitOccurrence(personEverything, conn);
		writeConditionOccurrences(personEverything, conn);
		writeDrugExposures(personEverything, conn);
		writeObservations(personEverything, conn);
		writeMeasurements(personEverything, conn);
		Database.commit(conn);
	}

	private static void writeFhirResources(OmopPersonEverythingFactory person, Connection conn) {
		PatientEverythingParser patient = person.getFhirPatientEverything();
		String patientId = patient.getPatient().getId();
		List<String> resourceList = patient.getResourceTypes();
		for (String fhirResource : resourceList) {
			String resourceName = null;
			if (fhirResource != null && fhirResource.lastIndexOf(".") > 0) {
				resourceName = fhirResource.substring(fhirResource.lastIndexOf(".") + 1, fhirResource.length());
			}
			FhirResourceDvo dvo = new FhirResourceDvo();
			dvo.setPatientId(patientId);
			dvo.setResourceType(fhirResource);
			dvo.setResourceName(resourceName);
			Dao.insert(dvo, conn);
		}
	}

	private static void writePatient(OmopPersonEverythingFactory person, Connection conn) {
		PersonDvo dvo = person.getPerson();
		Dao.insert(dvo, conn);
	}

	private static void writeVisitOccurrence(OmopPersonEverythingFactory person, Connection conn) {
		List<VisitOccurrenceDvo> visitList = person.getVisitOccurrenceList();
		for (VisitOccurrenceDvo dvo : visitList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeConditionOccurrences(OmopPersonEverythingFactory person, Connection conn) {
		List<ConditionOccurrenceDvo> conList = person.getConditionOccurrenceList();
		for (ConditionOccurrenceDvo dvo : conList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeDrugExposures(OmopPersonEverythingFactory person, Connection conn) {
		List<DrugExposureDvo> drugExposureList = person.getDrugExposureList();
		for (DrugExposureDvo dvo : drugExposureList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeMeasurements(OmopPersonEverythingFactory person, Connection conn) {
		List<MeasurementDvo> measList = person.getMeasurementList();
		for (MeasurementDvo dvo : measList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeObservations(OmopPersonEverythingFactory person, Connection conn) {
		List<ObservationDvo> observationList = person.getObservationList();
		for (ObservationDvo dvo : observationList) {
			Dao.insert(dvo, conn);
		}
	}

}
