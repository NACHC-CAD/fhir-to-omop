package org.nachc.tools.fhirtoomop.omop.person.factory.builder.procedure;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.fhir.parser.procedure.ProcedureParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ProcedureOccurrenceDvo;

public class OmopProcedureBuilder {

	public OmopPerson omopPerson;

	public FhirPatient fhirPatient;

	private Connection conn;

	public OmopProcedureBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.omopPerson = omopPerson;
		this.fhirPatient = fhirPatient;
		this.conn = conn;
	}

	public void build() {
		List<ProcedureOccurrenceDvo> rtn = new ArrayList<ProcedureOccurrenceDvo>();
		List<ProcedureParser> list = this.fhirPatient.getProcedureList();
		for(ProcedureParser proc : list) {
			rtn.add(getProcedureOccurenceDvo(proc));
		}
		omopPerson.setProcedureOccurrenceList(rtn);
	}

	private ProcedureOccurrenceDvo getProcedureOccurenceDvo(ProcedureParser proc) {
		ProcedureOccurrenceDvo dvo = new ProcedureOccurrenceDvo();
		// primary key
		Integer procId = FhirToOmopIdGenerator.getId("procedure_occurrence", "procedure_occurrence_id", conn);
		dvo.setProcedureOccurrenceId(procId);
		// patient id
		Integer omopPatientId = this.omopPerson.getPerson().getPersonId();
		dvo.setPersonId(omopPatientId);
		// encounter id
		String fhirEncounterId = proc.getEncounterId();
		Integer omopVisitId = this.omopPerson.getOmopEncounterId(fhirEncounterId);
		dvo.setVisitOccurrenceId(omopVisitId);
		// procedure concept id
		Coding procCoding = proc.getProcedure();
		ConceptDvo procConcept = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(procCoding, conn);
		if(procConcept != null) {
			dvo.setProcedureConceptId(procConcept.getConceptId());
		} else {
			dvo.setProcedureConceptId(0);
		}
		dvo.setProcedureDate(proc.getStartDate());
		dvo.setProcedureEndDate(proc.getEndDate());
		// TODO: (JEG) hard coding this to primary procedure for now
		dvo.setProcedureTypeConceptId(44786630);
		// done
		return dvo;
	}

}
