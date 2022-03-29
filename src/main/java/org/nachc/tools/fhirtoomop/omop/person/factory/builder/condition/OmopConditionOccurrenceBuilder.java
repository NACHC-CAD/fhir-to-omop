package org.nachc.tools.fhirtoomop.omop.person.factory.builder.condition;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;

public class OmopConditionOccurrenceBuilder {

	//
	// instance variables
	//

	private OmopPerson omopPerson;

	private FhirPatient fhirPatient;

	private Connection conn;

	//
	// constructor
	//

	public OmopConditionOccurrenceBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.omopPerson = omopPerson;
		this.fhirPatient = fhirPatient;
		this.conn = conn;
	}

	public void build() {
		buildConditionList();
	}

	//
	// implementation
	//

	private void buildConditionList() {
		Integer personId = this.omopPerson.getPerson().getPersonId();
		List<ConditionOccurrenceDvo> rtn = new ArrayList<ConditionOccurrenceDvo>();
		List<ConditionParser> conList = fhirPatient.getConditionList();
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
		this.omopPerson.setConditionOccurrenceList(rtn);
	}

}
