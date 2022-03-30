package org.nachc.tools.fhirtoomop.omop.write.singlepatient;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.FhirResourceDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPersonToDatabase {

	public static void exec(OmopPerson omopPerson, Connection conn) {
		writeFhirResources(omopPerson, conn);
		writePatient(omopPerson, conn);
		writeVisitOccurrence(omopPerson, conn);
		writeConditionOccurrences(omopPerson, conn);
		writeDrugExposures(omopPerson, conn);
		writeObservations(omopPerson, conn);
		writeMeasurements(omopPerson, conn);
		writeProcedures(omopPerson, conn);
		Database.commit(conn);
	}

	private static void writeFhirResources(OmopPerson person, Connection conn) {
		String patientId = person.getPatientId();
		List<String> resourceList = person.getResourceList();
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

	private static void writePatient(OmopPerson person, Connection conn) {
		PersonDvo dvo = person.getPerson();
		Dao.insert(dvo, conn);
	}

	private static void writeVisitOccurrence(OmopPerson person, Connection conn) {
		List<VisitOccurrenceDvo> visitList = person.getVisitOccurrenceList();
		for (VisitOccurrenceDvo dvo : visitList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeConditionOccurrences(OmopPerson person, Connection conn) {
		List<ConditionOccurrenceDvo> conList = person.getConditionOccurrenceList();
		for (ConditionOccurrenceDvo dvo : conList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeDrugExposures(OmopPerson person, Connection conn) {
		List<DrugExposureDvo> drugExposureList = person.getDrugExposureList();
		for (DrugExposureDvo dvo : drugExposureList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeMeasurements(OmopPerson person, Connection conn) {
		List<MeasurementDvo> measList = person.getMeasurementList();
		for (MeasurementDvo dvo : measList) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeProcedures(OmopPerson person, Connection conn) {
		List<ProcedureOccurrenceDvo> list = person.getProcedureOccurrenceList();
		for (ProcedureOccurrenceDvo dvo : list) {
			Dao.insert(dvo, conn);
		}
	}

	private static void writeObservations(OmopPerson person, Connection conn) {
		log.debug("Doing read...");
		List<ObservationDvo> observationList = person.getObservationList();
		log.debug("Doing write...");
		for (ObservationDvo dvo : observationList) {
			Dao.insert(dvo, conn);
		}
		log.debug("DONE");
	}

}
