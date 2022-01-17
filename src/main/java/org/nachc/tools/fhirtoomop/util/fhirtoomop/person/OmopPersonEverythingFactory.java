package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.OmopVisitOccurrenceFactory;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

/**
 * 
 * This is the class that turns a FHIR Patient/$everything resource into an OMOP
 * Person data value object (dvo).
 *
 */

public class OmopPersonEverythingFactory {

	//
	// instance variables
	//
	
	private String json;

	private Connection conn;

	private PatientEverythingParser patientEverything;

	private PersonDvo person;
	
	private List<VisitOccurrenceDvo> visitOccurrenceList;

	//
	// constructors
	//
	
	public OmopPersonEverythingFactory(PatientEverythingParser patientEverything, Connection conn) {
		this.conn = conn;
		this.patientEverything = patientEverything;
	}

	public OmopPersonEverythingFactory(String json, Connection conn) {
		this.json = json;
		this.conn = conn;
		this.patientEverything = new PatientEverythingParser(json);
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
		if(this.person == null) {
			this.person = new OmopPersonFactory(patientEverything, conn).getPerson();
		}
		return this.person;
	}

	public List<VisitOccurrenceDvo> getVisitOccurrenceList() {
		if(this.visitOccurrenceList == null) {
			OmopVisitOccurrenceFactory visitParser = new OmopVisitOccurrenceFactory(this, conn);
			this.visitOccurrenceList = visitParser.getVisitOccurencesList();
		}
		return this.visitOccurrenceList;
	}
	
}
