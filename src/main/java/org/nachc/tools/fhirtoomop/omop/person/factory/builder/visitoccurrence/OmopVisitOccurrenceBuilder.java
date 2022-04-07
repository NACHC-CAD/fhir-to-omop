package org.nachc.tools.fhirtoomop.omop.person.factory.builder.visitoccurrence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;

public class OmopVisitOccurrenceBuilder {

	public OmopPerson omopPerson;

	public FhirPatient fhirPatient;

	private Connection conn;

	public OmopVisitOccurrenceBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.omopPerson = omopPerson;
		this.fhirPatient = fhirPatient;
		this.conn = conn;
	}

	public void build() {
		buildVisitOccurencesList();
	}

	private void buildVisitOccurencesList() {
		PersonDvo person = omopPerson.getPerson();
		List<VisitOccurrenceDvo> rtn = new ArrayList<VisitOccurrenceDvo>();
		List<EncounterParser> encounterList = fhirPatient.getEncounterList();
		for (EncounterParser enc : encounterList) {
			Integer visitOccurrenceId = FhirToOmopIdGenerator.getId("visit_occurrence", "visit_occurrence_id", conn);
			VisitOccurrenceDvo dvo = getVisitOccurrenceDvo(enc, person, visitOccurrenceId);
			rtn.add(dvo);
		}
		this.omopPerson.setVisitOccurrenceList(rtn);
	}

	private VisitOccurrenceDvo getVisitOccurrenceDvo(EncounterParser enc, PersonDvo person, Integer visitOccurrenceId) {
		VisitOccurrenceDvo dvo = new VisitOccurrenceDvo();
		dvo.setVisitOccurrenceId(visitOccurrenceId);
		dvo.setPersonId(person.getPersonId());
		dvo.setVisitStartDate(enc.getStartDate());
		dvo.setVisitEndDate(enc.getEndDate());
		dvo.setVisitSourceValue(enc.getEncounterId());
		// types
		ConceptDvo visitConcept = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(enc.getEncounterType(), conn);
		if(visitConcept != null && visitConcept.getConceptId() != null) {
			dvo.setVisitConceptId(visitConcept.getConceptId());
		} else {
			dvo.setVisitConceptId(OmopConceptConstants.getVisitIsOtherType());
		}
		dvo.setVisitTypeConceptId(0);
		return dvo;
	}

}
