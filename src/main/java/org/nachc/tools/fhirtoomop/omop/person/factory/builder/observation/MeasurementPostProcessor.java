package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.yaorma.util.time.TimeUtil;

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
			if (dvo.getMeasurementEventId() == null && dvo.getMeasEventFieldConceptId() == null) {
				dvo.setMeasurementEventId(dvo.getMeasurementId() + "");
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			if (dvo.getMeasEventFieldConceptId() == null) {
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			if (dvo.getMeasurementEventId() == null) {
				dvo.setMeasurementEventId(dvo.getMeasurementId() + "");
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			// provider
			if (dvo.getProviderId() == null) {
				dvo.setProviderId(1);
			}
			// range
			if (dvo.getRangeHigh() == null) {
				dvo.setRangeHigh(dvo.getValueAsNumber());
			}
			if (dvo.getRangeLow() == null) {
				dvo.setRangeLow(dvo.getValueAsNumber());
			}
			if (dvo.getRangeHigh() == null) {
				dvo.setRangeHigh("0");
			}
			if (dvo.getRangeLow() == null) {
				dvo.setRangeLow("0");
			}
			// value as concept
			if (dvo.getValueAsConceptId() == null) {
				dvo.setValueAsConceptId(0);
			}
			// source
			if (dvo.getMeasurementSourceConceptId() == null) {
				dvo.setMeasurementSourceConceptId(dvo.getMeasurementConceptId());
			}
			if (dvo.getMeasurementSourceConceptId() == null) {
				dvo.setMeasurementSourceConceptId(0);
			}
			// time
			if (dvo.getMeasurementTime() == null && dvo.getMeasurementDatetime() != null) {
				dvo.setMeasurementTime(TimeUtil.format(dvo.getMeasurementDatetime()));
			}
			// unit
			if (dvo.getUnitSourceConceptId() == null) {
				dvo.setUnitSourceConceptId(dvo.getUnitConceptId());
			}
			if (dvo.getUnitSourceValue() == null) {
				dvo.setUnitSourceValue("Not Available");
			}
			// source value
			if (dvo.getMeasurementSourceValue() == null) {
				dvo.setMeasurementSourceValue("Not Available");
			}
			if (dvo.getValueSourceValue() == null) {
				dvo.setValueSourceValue(dvo.getMeasurementSourceValue());
			}
			if (dvo.getValueSourceValue() == null) {
				dvo.setValueSourceValue(dvo.getValueAsNumber());
			}
			if (dvo.getValueSourceValue() == null && dvo.getValueAsConceptId() != null) {
				dvo.setValueSourceValue(dvo.getValueAsConceptId().toString());
			}
			if (dvo.getValueSourceValue() == null) {
				dvo.setValueSourceValue("Not Available");
			}
		}
	}

}
