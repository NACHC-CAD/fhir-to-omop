package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.util.constants.OmopConceptConstants;
import org.nachc.tools.fhirtoomop.util.mapping.OperatorMapping;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.yaorma.util.time.TimeUtil;

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
		for (ObservationDvo obs : obsList) {
			addAdditionalObsValues(obs);
		}
	}

	public void addAdditionalObsValues(ObservationDvo dvo) {
		// value as string
		if (dvo.getValueAsString() == null && dvo.getValueAsNumber() != null) {
			dvo.setValueAsString(dvo.getValueAsNumber().toString());
		}
		if (dvo.getValueAsString() == null) {
			dvo.setValueAsString("Not Availavble");
		}
		// observation event
		if (dvo.getObservationEventId() == null && dvo.getObservationId() != null) {
			dvo.setObservationEventId(new Long(dvo.getObservationId()));
		}
		if (dvo.getObsEventFieldConceptId() == null) {
			dvo.setObsEventFieldConceptId(OmopConceptConstants.getObservationTableConceptId());
		}
		// provider
		if (dvo.getProviderId() == null) {
			dvo.setProviderId(1);
		}
		// qualifier
		if (dvo.getQualifierConceptId() == null) {
			dvo.setQualifierConceptId(OperatorMapping.get("="));
		}
		if (dvo.getQualifierSourceValue() == null) {
			dvo.setQualifierSourceValue("=");
		}
		if (dvo.getUnitSourceValue() == null) {
			dvo.setUnitSourceValue("Not Available");
		}
		// source value
		if (dvo.getValueSourceValue() == null) {
			dvo.setValueSourceValue(dvo.getValueAsString());
		}
		if (dvo.getValueSourceValue() == null) {
			dvo.setValueSourceValue("Not Available");
		}
		if (dvo.getObservationSourceConceptId() == null) {
			dvo.setObservationSourceConceptId(0);
		}
		if (dvo.getObservationSourceValue() == null) {
			dvo.setObservationSourceValue(dvo.getValueAsString());
		}
		if (dvo.getObservationSourceValue() == null) {
			dvo.setObservationSourceValue("Not Available");
		}
		// units
		if (dvo.getUnitConceptId() == null) {
			dvo.setUnitConceptId(OmopConceptConstants.getIsScalarMeasurementUnitsConceptId());
		}
		if (dvo.getObservationDatetime() == null && dvo.getObservationDate() != null) {
			dvo.setObservationDatetime(dvo.getObservationDate());
		}
		if (dvo.getObservationEventId() == null && dvo.getObsEventFieldConceptId() == null) {
			dvo.setObservationEventId(new Long(dvo.getObservationId()));
			dvo.setObsEventFieldConceptId(OmopConceptConstants.getObservationTableConceptId());
		}
	}

}
