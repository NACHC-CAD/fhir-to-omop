package org.nachc.tools.fhirtoomop.omop.person.factory.builder.visitoccurrence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;
import org.nachc.tools.omop.yaorma.dvo.VisitOccurrenceDvo;
import org.yaorma.util.time.TimeUtil;

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
		// create the dvo
		VisitOccurrenceDvo dvo = new VisitOccurrenceDvo();
		// visit and person ids
		dvo.setVisitOccurrenceId(visitOccurrenceId);
		dvo.setPersonId(person.getPersonId());
		// dates
		dvo.setVisitStartDate(enc.getStartDate());
		dvo.setVisitEndDate(enc.getEndDate());
		if(dvo.getVisitEndDate() == null) {
			dvo.setVisitEndDate(dvo.getVisitStartDate());
		}
		dvo.setVisitStartDatetime(TimeUtil.format(dvo.getVisitStartDate(), "yyyy-MM-dd"));
		dvo.setVisitEndDatetime(TimeUtil.format(dvo.getVisitEndDate(), "yyyy-MM-dd"));
		dvo.setVisitSourceValue(enc.getEncounterId());
		// visit concept
		Coding encounterType = enc.getEncounterType();
		ConceptDvo visitConcept = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(encounterType, conn);
		if(visitConcept != null && visitConcept.getConceptId() != null && "Visit".equals(visitConcept.getDomainId())) {
			dvo.setVisitConceptId(visitConcept.getConceptId());
			dvo.setVisitSourceConceptId(visitConcept.getConceptId());
		} else {
			dvo.setVisitConceptId(OmopConceptConstants.getVisitIsOtherType());
			dvo.setVisitSourceConceptId(OmopConceptConstants.getVisitIsOtherType());
		}
		// visit type
		dvo.setVisitTypeConceptId(OmopConceptConstants.getVisitTypeIsFromEmr());
		dvo.setCareSiteId(1);
		// admitted from
		dvo.setAdmittedFromConceptId(OmopConceptConstants.getDefaultVisitAdmittedFrom());
		dvo.setAdmittedFromSourceValue("Not Available");
		// discharged to
		dvo.setDischargedToConceptId(OmopConceptConstants.getDefaultDischargedTo());
		dvo.setDischargedToSourceValue("Not Available");
		// provider id
		dvo.setProviderId(1);
		
		return dvo;
	}

}
