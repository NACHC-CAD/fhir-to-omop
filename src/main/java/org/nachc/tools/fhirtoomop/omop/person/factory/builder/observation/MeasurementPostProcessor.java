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
			if(dvo.getMeasurementEventId() == null && dvo.getMeasEventFieldConceptId() == null) {
				dvo.setMeasurementEventId(dvo.getMeasurementId() + "");
				dvo.setMeasEventFieldConceptId(OmopConceptConstants.getMeasurementTable());
			}
			if(dvo.getProviderId() == null) {
				dvo.setProviderId(1);
			}
			
		}
	}

}
