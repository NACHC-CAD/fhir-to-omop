package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class ObservationPostProcessor {

	private OmopPerson person;
	
	private Connection conn;

	public ObservationPostProcessor(OmopPerson person, Connection conn) {
		super();
		this.person = person;
		this.conn = conn;
	}

	public void build() {
		List<ObservationDvo> obsList = person.getObservationList();
		for(ObservationDvo obs : obsList) {
			if(obs.getObservationEventId() == null && obs.getObsEventFieldConceptId() == null) {
				obs.setObservationEventId(obs.getObservationId() + "");
				obs.setObsEventFieldConceptId(OmopConceptConstants.getObservationTableConceptId());
			}
		}
	}
	
}
