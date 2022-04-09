package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class ObservationPostProcessor {

	public void exec(OmopPerson person, Connection conn) {
		List<ObservationDvo> obsList = person.getObservationList();
		for(ObservationDvo obs : obsList) {
			if(obs.getObservationEventId() == null && obs.getObsEventFieldConceptId() == null) {
				obs.setObservationEventId(obs.getObservationId() + "");
				obs.setObsEventFieldConceptId(OmopConceptConstants.getObservationTableConceptId());
			}
		}
	}
	
}
