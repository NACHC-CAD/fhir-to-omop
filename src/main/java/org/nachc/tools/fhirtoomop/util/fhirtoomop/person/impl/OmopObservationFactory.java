package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;
import org.nachc.tools.fhirtoomop.util.mapping.impl.FhirToOmopConceptMapper;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class OmopObservationFactory {

	private OmopPersonEverythingFactory person;
	
	private Connection conn;
	
	public OmopObservationFactory(OmopPersonEverythingFactory person, Connection conn) {
		this.person = person;
		this.conn = conn;
	}
	
	public List<ObservationDvo> getObservationList() {
		List<ObservationDvo> rtn = new ArrayList<ObservationDvo>();
		List<ObservationParser> obsList = this.person.getFhirPatientEverything().getObservationList();
		for(ObservationParser obs : obsList) {
			ObservationDvo dvo = getObservation(obs);
			rtn.add(dvo);
		}
		return rtn;
	}
	
	private ObservationDvo getObservation(ObservationParser obs) {
		ObservationDvo dvo = new ObservationDvo();
		// observation id
		dvo.setObservationId(FhirToOmopIdGenerator.getId("observation", "observation_id", conn));
		// observation concept id
		Coding obsCoding = obs.getObservationCode();
		ConceptDvo conceptDvo = FhirToOmopConceptMapper.getOmopConceptForFhirCoding(obsCoding, conn);
		Integer obsConceptId = conceptDvo == null ? 0 : conceptDvo.getConceptId();
		dvo.setObservationConceptId(obsConceptId);
		return dvo;
	}

}
