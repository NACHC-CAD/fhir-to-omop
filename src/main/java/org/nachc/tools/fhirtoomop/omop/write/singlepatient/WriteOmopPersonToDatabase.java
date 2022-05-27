package org.nachc.tools.fhirtoomop.omop.write.singlepatient;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.FhirResourceDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationPeriodDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;
import org.yaorma.util.time.TimeUtil;

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
		String patientId = person.getPersonIdStr();
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
			try {
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}

	private static void writePatient(OmopPerson person, Connection conn) {
		PersonDvo dvo = person.getPerson();
		try {
			Dao.insert(dvo, conn);
			writeObservationPeriod(dvo, conn);
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}

	private static void writeObservationPeriod(PersonDvo personDvo, Connection conn) {
		try {
			ObservationPeriodDvo dvo = new ObservationPeriodDvo();
			dvo.setObservationPeriodStartDate(TimeUtil.getDateForYyyy_Mm_Dd("1900-01-01"));
			dvo.setObservationPeriodEndDate(TimeUtil.getDateForYyyy_Mm_Dd("2100-01-01"));
			dvo.setPersonId(personDvo.getPersonId());
			int id = FhirToOmopIdGenerator.getId("observation_period", "observation_period_id", conn);
			dvo.setObservationPeriodId(id);
			dvo.setPeriodTypeConceptId(44814724);
			Dao.insert(dvo, conn);
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	private static void writeVisitOccurrence(OmopPerson person, Connection conn) {
		List<VisitOccurrenceDvo> visitList = person.getVisitOccurrenceList();
		for (VisitOccurrenceDvo dvo : visitList) {
			try {
				if(dvo.getVisitStartDate() == null) {
					dvo.setVisitStartDate(AppParams.getDateNotFound());
				}
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}

	private static void writeConditionOccurrences(OmopPerson person, Connection conn) {
		List<ConditionOccurrenceDvo> conList = person.getConditionOccurrenceList();
		int retryCount = 5;
		for (ConditionOccurrenceDvo dvo : conList) {
			int cnt = 0;
			while(cnt < retryCount) {
				cnt++;
				try {
					writeSingleConditionOccurrences(dvo, conn);
					break;
				} catch(Exception exp) {
					if(cnt < retryCount) {
						Integer id = FhirToOmopIdGenerator.getId("condition_occurrence", "condition_occurrence_id", conn);
						dvo.setConditionOccurrenceId(id);
					} else {
						exp.printStackTrace();
					}
				}
			}
		}
	}

	private static void writeSingleConditionOccurrences(ConditionOccurrenceDvo dvo, Connection conn) {
		try {
			if(dvo.getConditionStartDate() == null) {
				dvo.setConditionStartDate(AppParams.getDateNotFound());
			}
			Dao.insert(dvo, conn);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
	private static void writeDrugExposures(OmopPerson person, Connection conn) {
		List<DrugExposureDvo> drugExposureList = person.getDrugExposureList();
		for (DrugExposureDvo dvo : drugExposureList) {
			try {
				if(dvo.getDrugExposureStartDate() == null) {
					dvo.setDrugExposureStartDate(AppParams.getDateNotFound());
				}
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}

	private static void writeMeasurements(OmopPerson person, Connection conn) {
		List<MeasurementDvo> measList = person.getMeasurementList();
		for (MeasurementDvo dvo : measList) {
			try {
				if(dvo.getMeasurementDate() == null) {
					dvo.setMeasurementDate(AppParams.getDateNotFound());
				}
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}

	private static void writeProcedures(OmopPerson person, Connection conn) {
		List<ProcedureOccurrenceDvo> list = person.getProcedureOccurrenceList();
		for (ProcedureOccurrenceDvo dvo : list) {
			try {
				if(dvo.getProcedureDate() == null) {
					dvo.setProcedureDate(AppParams.getDateNotFound());
				}
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}

	private static void writeObservations(OmopPerson person, Connection conn) {
		log.debug("Doing read...");
		List<ObservationDvo> observationList = person.getObservationList();
		log.debug("Doing write...");
		for (ObservationDvo dvo : observationList) {
			try {
				if(dvo.getObservationDate() == null) {
					dvo.setObservationDate(AppParams.getDateNotFound());
				}
				Dao.insert(dvo, conn);
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
		log.debug("DONE");
	}

}
