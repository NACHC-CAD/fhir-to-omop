package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;

public class MeasurementPostProcessor {

	private OmopPerson omopPerson;

	private Connection conn;

	public MeasurementPostProcessor(OmopPerson omopPerson, Connection conn) {
		this.omopPerson = omopPerson;
		this.conn = conn;
	}

	public void build() {
		List<MeasurementDvo> measList = omopPerson.getMeasurementList();
		for (MeasurementDvo dvo : measList) {
			// event
			if(dvo.getMeasurementEventId() == null && dvo.getMeasEventFieldConceptId() == null) {
				dvo.setMeasurementEventId(dvo.getMeasurementId() + "");
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			if(dvo.getMeasEventFieldConceptId() == null) {
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			if(dvo.getMeasurementEventId() == null) {
				dvo.setMeasurementEventId(dvo.getMeasurementId() + "");
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			// provider
			if(dvo.getProviderId() == null) {
				dvo.setProviderId(1);
			}
			// range
			if(dvo.getRangeHigh() == null) {
				dvo.setRangeHigh(dvo.getValueAsNumber());
			}
			if(dvo.getRangeLow() == null) {
				dvo.setRangeLow(dvo.getValueAsNumber());
			}
			if(dvo.getRangeHigh() == null) {
				dvo.setRangeHigh("0");
			}
			if(dvo.getRangeLow() == null) {
				dvo.setRangeLow("0");
			}		
			// value as concept
			if(dvo.getValueAsConceptId() == null) {
				dvo.setValueAsConceptId(1);
			}
		}
	}

}
