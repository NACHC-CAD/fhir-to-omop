package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.mapping.ConditionMapping;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;

public class OmopConditionFactory {

	//
	// instance variables
	//

	public OmopPersonEverythingFactory omopPersonEverything;

	private Connection conn;

	//
	// constructor
	//

	public OmopConditionFactory(OmopPersonEverythingFactory omopPersonEverything, Connection conn) {
		this.omopPersonEverything = omopPersonEverything;
		this.conn = conn;
	}

	//
	// implementation
	//

	public List<ConditionOccurrenceDvo> getConditionList() {
		PatientEverythingParser fhirPatient = this.omopPersonEverything.getFhirPatientEverything();
		List<ConditionOccurrenceDvo> rtn = new ArrayList<ConditionOccurrenceDvo>();
		List<ConditionParser> conList = fhirPatient.getConditionList();
		for (ConditionParser con : conList) {
			ConditionOccurrenceDvo dvo = new ConditionOccurrenceDvo();
			Integer id = FhirToOmopIdGenerator.getId("condition_occurrence", "condition_occurrence_id", conn);
			dvo.setConditionOccurrenceId(id);
			dvo.setConditionStartDate(con.getStartDate());
			dvo.setConditionEndDate(con.getEndDate());
			dvo.setConditionSourceValue(con.getCode());
			ConceptDvo conceptDvo = ConditionMapping.mapFhirCodingToOmopStandardConcept(con.getCoding(), conn);
			dvo.setConditionConceptId(conceptDvo == null ? null : conceptDvo.getConceptId());
			rtn.add(dvo);
		}
		return rtn;
	}

}
