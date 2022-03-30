package org.nachc.tools.fhirtoomop.omop.person;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
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

	private List<FhirPatient> fhirPatientList = new ArrayList<FhirPatient>();

	private List<VisitOccurrenceDvo> visitOccurrenceList = new ArrayList<VisitOccurrenceDvo>();

	private List<ConditionOccurrenceDvo> conditionOccurrenceList = new ArrayList<ConditionOccurrenceDvo>();

	private List<ObservationDvo> observationList = new ArrayList<ObservationDvo>();

	private List<MeasurementDvo> measurementList = new ArrayList<MeasurementDvo>();

	private List<DrugExposureDvo> drugExposureList = new ArrayList<DrugExposureDvo>();

	private List<ProcedureOccurrenceDvo> procedureOccurrenceList = new ArrayList<ProcedureOccurrenceDvo>();
	
	private List<String> resourceList = new ArrayList<String>();

	public Integer getPersonId() {
		try {
			Integer id = this.person.getPersonId();
			return id;
		} catch(Exception exp) {
			return null;
		}
	}
	
	public String getPersonIdStr() {
		try {
			Integer id = this.person.getPersonId();
			if(id != null) {
				return id + "";
			} else {
				return null;
			}
		} catch(Exception exp) {
			return null;
		}
	}
	
	public Integer getOmopEncounterId(String fhirEncounterId) {
		List<VisitOccurrenceDvo> visitList = this.getVisitOccurrenceList();
		for (VisitOccurrenceDvo dvo : visitList) {
			String sourceId = dvo.getVisitSourceValue();
			if (sourceId != null && sourceId.equals(fhirEncounterId)) {
				return dvo.getVisitOccurrenceId();
			}
		}
		return null;
	}

	public VisitOccurrenceDvo getVisitOccurrenceByFhirId(String fhirId) {
		for (VisitOccurrenceDvo dvo : this.visitOccurrenceList) {
			if (fhirId != null && fhirId.equals(dvo.getVisitSourceValue())) {
				return dvo;
			}
		}
		return null;
	}

}
