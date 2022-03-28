package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverything;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;

public class OmopConditionFactory {

	//
	// instance variables
	//

	public OmopPersonEverything omopPersonEverything;

	private Connection conn;

	//
	// constructor
	//

	public OmopConditionFactory(OmopPersonEverything omopPersonEverything, Connection conn) {
		this.omopPersonEverything = omopPersonEverything;
		this.conn = conn;
	}

	//
	// implementation
	//
	
	public List<ConditionOccurrenceDvo> getConditionList(PatientEverythingParser fhirPatientEverything) {
		Integer personId = this.omopPersonEverything.getPerson().getPersonId();
		List<ConditionOccurrenceDvo> rtn = new ArrayList<ConditionOccurrenceDvo>();
		List<ConditionParser> conList = fhirPatientEverything.getConditionList();
		for (ConditionParser con : conList) {
			Integer id = FhirToOmopIdGenerator.getId("condition_occurrence", "condition_occurrence_id", conn);
			ConditionOccurrenceDvo dvo = new ConditionOccurrenceDvo();
			dvo.setPersonId(personId);
			dvo.setConditionOccurrenceId(id);
			dvo.setConditionStartDate(con.getStartDate());
			dvo.setConditionEndDate(con.getEndDate());
			dvo.setConditionSourceValue(con.getCode());
			ConceptDvo conceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(con.getCoding(), conn);
			dvo.setConditionConceptId(conceptDvo == null ? 0 : conceptDvo.getConceptId());
			// TODO: (JEG) Hardcoding this to EHR encounter diagnosis for now
			dvo.setConditionTypeConceptId(32020);
			rtn.add(dvo);
		}
		return rtn;
	}

}
