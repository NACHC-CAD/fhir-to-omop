package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopConditionFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopDrugExposureFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopMeasurementFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopObservationFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopVisitOccurrenceFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationDvoProxy;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationOrMeasurement;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

/**
 * 
 * This is the class that turns a FHIR Patient/$everything resource into an OMOP
 * Person data value object (dvo).
 *
 */

public class OmopPersonEverything {

	//
	// instance variables
	//

	private String json;

	private Connection conn;

	private PersonDvo person;

	private List<PatientEverythingParser> patientEverythingList = new ArrayList<PatientEverythingParser>();

	private List<VisitOccurrenceDvo> visitOccurrenceList = new ArrayList<VisitOccurrenceDvo>();

	private List<ConditionOccurrenceDvo> conditionOccurrenceList = new ArrayList<ConditionOccurrenceDvo>();

	private List<ObservationDvoProxy> observationList = new ArrayList<ObservationDvoProxy>();

	private List<DrugExposureDvo> drugExposureList = new ArrayList<DrugExposureDvo>();
	
	private List<ProcedureOccurrenceDvo> procedureOccurrenceList = new ArrayList<ProcedureOccurrenceDvo>();

	//
	// constructors
	//

	protected OmopPersonEverything(String json, Connection conn) {
		this(new PatientEverythingParser(json), conn);
	}

	protected OmopPersonEverything(PatientEverythingParser patientEverything, Connection conn) {
		this.conn = conn;
		// person
		this.person = new OmopPersonFactory(patientEverything, conn).getPerson();
		/*
		// visit
		OmopVisitOccurrenceFactory visitParser = new OmopVisitOccurrenceFactory(this, conn);
		this.visitOccurrenceList = visitParser.getVisitOccurencesList(getFhirPatientEverything());
		// condition
		OmopConditionFactory conditionFactory = new OmopConditionFactory(this, conn);
		this.conditionOccurrenceList = conditionFactory.getConditionList();
		// drug exposure
		OmopDrugExposureFactory drugExpFactory = new OmopDrugExposureFactory(this, conn);
		this.drugExposureList = drugExpFactory.getDrugExposureList();
		// obs
		OmopObservationFactory obsFactory = new OmopObservationFactory(this, conn);
		this.observationList = obsFactory.getObservationList();
		*/
	}

	//
	// trivial getters and setters
	//

	public List<PatientEverythingParser> getFhirPatientEverythingList() {
		return this.patientEverythingList;
	}

	public PersonDvo getPerson() {
		return this.person;
	}

	public List<VisitOccurrenceDvo> getVisitOccurrenceList() {
		return this.visitOccurrenceList;
	}

	public List<ConditionOccurrenceDvo> getConditionOccurrenceList() {
		return this.conditionOccurrenceList;
	}

	public List<DrugExposureDvo> getDrugExposureList() {
		return this.drugExposureList;
	}

	public List<ObservationDvoProxy> getFhirObservationList() {
		return this.observationList;
	}

	public List<ProcedureOccurrenceDvo> getProcedureOccurrenceList() {
		return this.procedureOccurrenceList;
	}
	
	//
	// other getters and setters
	//

	public List<String> getResourceList() {
		List<String> rtn = new ArrayList<String>();
		for(PatientEverythingParser patient : this.patientEverythingList) {
			List<String> resourceList = patient.getResourceTypes();
			rtn.addAll(resourceList);
		}
		return rtn;
	}
	
	public String getPatientId() {
		for(PatientEverythingParser parser : this.patientEverythingList) {
			String rtn = parser.getPatient().getId();
			if(rtn != null) {
				return rtn;
			}
		}
		return null;
	}
	
	public VisitOccurrenceDvo getVisitOccurrenceByFhirId(String fhirId) {
		for(VisitOccurrenceDvo dvo : this.visitOccurrenceList) {
			if(fhirId != null && fhirId.equals(dvo.getVisitSourceValue())) {
				return dvo;
			}
		}
		return null;
	}

	//
	// observation stuff
	//

	public List<ObservationDvoProxy> getObservationProxyList() {
		return this.observationList;
	}
	
	public List<MeasurementDvo> getMeasurementList() {
		List<ObservationDvoProxy> allObs = this.getFhirObservationList();
		List<MeasurementDvo> rtn = new ArrayList<MeasurementDvo>();
		for (ObservationDvoProxy proxy : allObs) {
			if (proxy.getObservationOrMeasurement() == ObservationOrMeasurement.MEASUREMENT) {
				MeasurementDvo meas = OmopMeasurementFactory.getMeasurement(proxy);
				rtn.add(meas);
			}
		}
		return rtn;
	}

	public List<ObservationDvo> getObservationList() {
		List<ObservationDvoProxy> allObs = this.getFhirObservationList();
		List<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		for (ObservationDvoProxy proxy : allObs) {
			if (proxy.getObservationOrMeasurement() == ObservationOrMeasurement.OBSERVATION) {
				rtn.add(proxy.getDvo());
			}
		}
		return rtn;
	}

	//
	// convenience methods to get ids
	//

	public Integer getOmopPatientId() {
		return this.getPerson().getPersonId();
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

}
