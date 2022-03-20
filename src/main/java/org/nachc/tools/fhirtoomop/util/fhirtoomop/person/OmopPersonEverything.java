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

	private PatientEverythingParser patientEverything;

	private PersonDvo person;

	private List<VisitOccurrenceDvo> visitOccurrenceList;

	private List<ConditionOccurrenceDvo> conditionOccurrenceList;

	private List<ObservationDvoProxy> observationList;

	private List<DrugExposureDvo> drugExposureList;

	//
	// constructors
	//

	public OmopPersonEverything(String json, Connection conn) {
		this(new PatientEverythingParser(json), conn);
	}

	public OmopPersonEverything(PatientEverythingParser patientEverything, Connection conn) {
		this.conn = conn;
		this.patientEverything = patientEverything;
		// person
		this.person = new OmopPersonFactory(patientEverything, conn).getPerson();
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
	}

	//
	// trivial getters and setters
	//

	public PatientEverythingParser getFhirPatientEverything() {
		return this.patientEverything;
	}

	//
	// getters and setters
	//

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

	//
	// observation stuff
	//

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
