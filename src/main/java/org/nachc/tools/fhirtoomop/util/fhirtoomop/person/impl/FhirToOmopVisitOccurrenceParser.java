package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

public class FhirToOmopVisitOccurrenceParser {

	public List<VisitOccurrenceDvo> getVisitOccurencesList(PatientEverythingParser patient) {
		List<VisitOccurrenceDvo> rtn = new ArrayList<VisitOccurrenceDvo>();
		List<EncounterParser> encounterList = patient.getEncounterList();
		Integer visitOccurrenceId = FhirToOmopIdGenerator.getId("visit_occurrence", "visit_occurrence_id");
		visitOccurrenceId--;
		for (EncounterParser enc : encounterList) {
			visitOccurrenceId++;
			VisitOccurrenceDvo dvo = getVisitOccurrenceDvo(enc, visitOccurrenceId);
			rtn.add(dvo);
		}
		return rtn;
	}

	private VisitOccurrenceDvo getVisitOccurrenceDvo(EncounterParser enc, Integer visitOccurrenceId) {
		VisitOccurrenceDvo dvo = new VisitOccurrenceDvo();
		dvo.setVisitOccurrenceId(visitOccurrenceId);
		dvo.setVisitStartDate(enc.getStartDate());
		dvo.setVisitEndDate(enc.getEndDate());
		dvo.setVisitSourceValue(enc.getEncounterIdUncAndQual());
		return dvo;
	}

}
