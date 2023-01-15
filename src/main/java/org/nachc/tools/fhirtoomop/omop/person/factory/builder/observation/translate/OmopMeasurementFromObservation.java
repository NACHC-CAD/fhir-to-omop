package org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation.translate;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.fhir.parser.observation.component.ObservationComponentParser;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.mapping.OperatorMapping;
import org.nachc.tools.omop.yaorma.dvo.MeasurementDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;

import lombok.Getter;

@Getter
public class OmopMeasurementFromObservation {

	private ObservationParser obsParser;
	
	private ObservationComponentParser compParser;
	
	private ObservationDvo obs;

	private Connection conn;
	
	public OmopMeasurementFromObservation(ObservationParser obsParser, ObservationComponentParser compParser, ObservationDvo obs, Connection conn) {
		this.obsParser = obsParser;
		this.compParser = compParser;
		this.obs = obs;
		this.conn = conn;
	}

	public MeasurementDvo buildMeasurement() {
		// create the dvo and set primary key
		MeasurementDvo dvo = new MeasurementDvo();
		int measurementId = FhirToOmopIdGenerator.getId("measurement", "measurement_id");
		dvo.setMeasurementId(measurementId);
		// person
		dvo.setPersonId(obs.getPersonId());
		// meas
		dvo.setMeasurementConceptId(obs.getObservationConceptId());
		dvo.setMeasurementSourceConceptId(obs.getObservationSourceConceptId());
		dvo.setMeasurementSourceValue(obs.getObservationSourceValue());
		// dates
		dvo.setMeasurementDate(obs.getObservationDate());
		dvo.setMeasurementDatetime(obs.getObservationDatetime());
		// type
		dvo.setMeasurementTypeConceptId(obs.getObservationTypeConceptId());
		dvo.setValueAsNumber(obs.getValueAsNumber());
		dvo.setValueAsConceptId(obs.getValueAsConceptId());
		dvo.setUnitConceptId(obs.getUnitConceptId());
		dvo.setUnitSourceValue(obs.getUnitSourceValue());
		dvo.setValueSourceValue(obs.getValueSourceValue());
		dvo.setVisitOccurrenceId(obs.getVisitOccurrenceId());
		dvo.setVisitDetailId(obs.getVisitDetailId());
		dvo.setMeasurementSourceConceptId(obs.getObservationSourceConceptId());
		dvo.setUnitSourceValue(obs.getUnitSourceValue());
		if(dvo.getUnitSourceValue() == null) {
			String units = "Not available";
			if(obsParser != null) {
				units = obsParser.getUnitsCodingDisplay();
			}
			if(compParser != null) {
				units = compParser.getUnitsCodingDisplay();
			}
			dvo.setUnitSourceValue(units);
		}
		dvo.setUnitSourceConceptId(obs.getUnitConceptId());
		dvo.setValueSourceValue(obs.getValueSourceValue());
		dvo.setMeasurementEventId(obs.getObservationEventId());
		dvo.setMeasEventFieldConceptId(obs.getObsEventFieldConceptId());
		String oper = null;
		if(this.obsParser != null) {
			oper = obsParser.getOperator();
		}
		if(this.compParser != null) {
			oper = compParser.getOperator();
		}
		if(oper == null) {
			oper = "=";
		}
		int operId = OperatorMapping.get(oper);
		dvo.setOperatorConceptId(operId);
		return dvo;
	}

}
