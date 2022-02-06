package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl;

import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs.ObservationDvoProxy;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

public class OmopMeasurementFactory {

	public static MeasurementDvo getMeasurement(ObservationDvoProxy proxy) {
		ObservationDvo obs = proxy.getDvo();
		MeasurementDvo dvo = new MeasurementDvo();
		dvo.setMeasurementId(obs.getObservationId());
		dvo.setPersonId(obs.getPersonId());
		dvo.setMeasurementConceptId(obs.getObservationConceptId());
		dvo.setMeasurementDate(obs.getObservationDate());
		dvo.setMeasurementDatetime(obs.getObservationDatetime());
		dvo.setMeasurementTypeConceptId(obs.getObservationTypeConceptId());
		// TODO: (JEG) need to get operator
		dvo.setValueAsNumber(obs.getValueAsNumber());
		dvo.setValueAsConceptId(obs.getValueAsConceptId());
		dvo.setUnitConceptId(obs.getUnitConceptId());
		// TODO: (JEG) need to get range
		// TODO: (JEG) need to get provider
		dvo.setVisitOccurrenceId(obs.getVisitOccurrenceId());
		dvo.setVisitDetailId(obs.getVisitDetailId());
		dvo.setMeasurementSourceConceptId(obs.getObservationSourceConceptId());
		dvo.setUnitSourceValue(obs.getUnitSourceValue());
		dvo.setUnitSourceConceptId(obs.getUnitConceptId());
		dvo.setValueSourceValue(obs.getValueSourceValue());
		dvo.setMeasurementEventId(obs.getObservationEventId());
		dvo.setMeasEventFieldConceptId(obs.getObsEventFieldConceptId());
		return dvo;	
	}

}
