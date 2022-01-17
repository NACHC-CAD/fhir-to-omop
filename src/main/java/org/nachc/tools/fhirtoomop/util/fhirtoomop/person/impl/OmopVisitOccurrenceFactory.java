package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

public class OmopVisitOccurrenceFactory {

	public OmopPersonEverythingFactory omopPersonEverything;

	private Connection conn;

	public OmopVisitOccurrenceFactory(OmopPersonEverythingFactory omopPersonEverything, Connection conn) {
		this.omopPersonEverything = omopPersonEverything;
		this.conn = conn;
	}

	public List<VisitOccurrenceDvo> getVisitOccurencesList() {
		PatientEverythingParser fhirPatient = omopPersonEverything.getFhirPatientEverything();
		PersonDvo person = omopPersonEverything.getPerson();
		List<VisitOccurrenceDvo> rtn = new ArrayList<VisitOccurrenceDvo>();
		List<EncounterParser> encounterList = fhirPatient.getEncounterList();
		Integer visitOccurrenceId = FhirToOmopIdGenerator.getId("visit_occurrence", "visit_occurrence_id", conn);
		visitOccurrenceId--;
		for (EncounterParser enc : encounterList) {
			visitOccurrenceId++;
			VisitOccurrenceDvo dvo = getVisitOccurrenceDvo(enc, person, visitOccurrenceId);
			rtn.add(dvo);
		}
		return rtn;
	}

	private VisitOccurrenceDvo getVisitOccurrenceDvo(EncounterParser enc, PersonDvo person, Integer visitOccurrenceId) {
		VisitOccurrenceDvo dvo = new VisitOccurrenceDvo();
		dvo.setVisitOccurrenceId(visitOccurrenceId);
		dvo.setPersonId(person.getPersonId());
		dvo.setVisitStartDate(enc.getStartDate());
		dvo.setVisitEndDate(enc.getEndDate());
		dvo.setVisitSourceValue(enc.getEncounterIdUncAndQual());
		return dvo;
	}

}
