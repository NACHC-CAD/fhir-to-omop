package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.ObservationParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.observation.enumerations.ObservationType;
import org.nachc.tools.fhirtoomop.util.omop.constants.OmopConstants;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * There's additional information required to (for example) decided if a FHIR
 * Observation is and OMOP Measurement or and OMOP Observation.
 * 
 * This class encapsulates that additional information.
 *
 */

@Slf4j
public class ObservationDvoProxy {

	//
	// instance variables
	//

	private ObservationDvo dvo;

	private ObservationType observationType;

	private ConceptDvo observationConceptDvo;

	private ConceptDvo valueConceptDvo;

	private ConceptDvo valueUnitsConceptDvo;

	private ObservationValueType valueType;

	private ObservationOrMeasurement observationOrMeasurement;

	//
	// constructor
	//

	public ObservationDvoProxy(ObservationDvo dvo, ObservationParser parser, Connection conn) {
		this.dvo = dvo;
		this.observationType = parser.getObservationType();
		this.setObservationOrMeasurement();
		this.setValueType(parser);
		this.observationConceptDvo = getConceptDvo(dvo.getObservationConceptId(), conn);
		this.valueConceptDvo = getConceptDvo(dvo.getValueAsConceptId(), conn);
		this.valueUnitsConceptDvo = getConceptDvo(dvo.getUnitConceptId(), conn);
		if(dvo.getObservationEventId() != null) {
			this.setParentFieldIdName(conn);
		}
		// log a warning if we did not get an observation concept (this shouldn't ever
		// happen, TODO: (JEG) maybe should be an exception)
		if (dvo != null && (dvo.getObservationConceptId() == null || dvo.getObservationConceptId() == 0)) {
			String display = parser.getObservationCodeDisplay();
			log.warn("COULD NOT GET CONCEPT FOR OBSERVATION: " + display);
		}
	}

	private ConceptDvo getConceptDvo(Integer conceptId, Connection conn) {
		if (conceptId == null) {
			return null;
		} else {
			return Dao.find(new ConceptDvo(), "concept_id", conceptId + "", conn);
		}
	}

	private void setObservationOrMeasurement() {
		switch (this.observationType) {
		case LABORATORY:
			this.observationOrMeasurement = ObservationOrMeasurement.MEASUREMENT;
			break;
		case VITAL_SIGNS:
			this.observationOrMeasurement = ObservationOrMeasurement.MEASUREMENT;
			break;
		default:
			this.observationOrMeasurement = ObservationOrMeasurement.OBSERVATION;
			break;
		}
	}

	private void setValueType(ObservationParser parser) {
		// set the value type
		if (parser.getValueCoding() != null) {
			this.valueType = ObservationValueType.CODED;
		} else if (parser.getValueAsNumber() != null) {
			this.valueType = ObservationValueType.QUANTITY;
		} else {
			this.valueType = ObservationValueType.STRING;
		}

	}

	private void setParentFieldIdName(Connection conn) {
		String str;
		if(this.observationOrMeasurement == ObservationOrMeasurement.MEASUREMENT) {
			str = OmopConstants.getMesurementIdFieldCode(conn);
		} else {
			str = OmopConstants.getObservationIdFieldCode(conn);
		}
		int key = Integer.parseInt(str);
		this.dvo.setObsEventFieldConceptId(key);
	}

	// -----------------
	//
	// all methods past here are trivial
	//
	// -----------------

	//
	// getters
	//

	public Integer getObservationId() {
		try {
			return this.dvo.getObservationId();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getObservationIdString() {
		if (this.getObservationId() == null) {
			return null;
		} else {
			return this.getObservationId() + "";
		}
	}

	public ObservationDvo getDvo() {
		return dvo;
	}

	public ObservationType getObservationType() {
		return observationType;
	}

	public ConceptDvo getObservationConceptDvo() {
		return observationConceptDvo;
	}

	public ObservationValueType getValueType() {
		return this.valueType;
	}

	public ConceptDvo getValueUnitsConceptDvo() {
		return valueUnitsConceptDvo;
	}

	public ObservationOrMeasurement getObservationOrMeasurement() {
		return observationOrMeasurement;
	}

	//
	// string manipulation methods
	//

	public String getName() {
		try {
			return this.observationConceptDvo.getConceptName();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsAsString() {
		if (this.valueUnitsConceptDvo != null) {
			ConceptDvo dvo = this.valueUnitsConceptDvo;
			String rtn = "";
			rtn += dvo.getConceptName();
			rtn += " (" + dvo.getVocabularyId() + "|" + dvo.getConceptCode() + ")";
			return rtn;
		} else {
			return dvo.getUnitSourceValue();
		}
	}

	//
	// method to get value as string
	//

	public String getValueAsString() {
		if (this.getValueType() == ObservationValueType.CODED) {
			String rtn = "";
			rtn += this.valueConceptDvo.getConceptName();
			rtn += "(" + this.valueConceptDvo.getVocabularyId() + "|" + this.valueConceptDvo.getConceptCode() + ")";
			return rtn;
		} else if (this.getValueType() == ObservationValueType.QUANTITY) {
			String rtn = "";
			rtn += this.dvo.getValueAsNumber();
			return rtn;
		} else {
			return this.dvo.getValueAsString();
		}
	}

	//
	// method to get value as display string
	//

	//
	// methods to get fixed with record and header
	//

	public static String getFixedWithHeaderRow() {
		String rtn = "";
		rtn += rpad("OBS_MEAS", 16);
		rtn += rpad("OBS_TYPE ", 16);
		rtn += rpad("OBSERVATION_ID ", 16);
		rtn += rpad("PARENT_ID ", 16);
		rtn += rpad("PARENT_COL_ID ", 16);
		rtn += rpad("OBS_CONCEPT_ID ", 16);
		rtn += rpad("VALUE_TYPE ", 16);
		rtn += rpad("VALUE_AS_STRING ", 64);
		rtn += rpad("UNITS ", 64);
		rtn += rpad("OBS_NAME ", 64);
		return rtn;
	}

	public String getAsFixedWidthString() {
		String rtn = "";
		rtn += rpad(this.getObservationOrMeasurement(), 16);
		rtn += rpad(this.getObservationType(), 16);
		rtn += rpad(dvo.getObservationId(), 16);
		rtn += rpad(dvo.getObservationEventId(), 16);
		rtn += rpad(dvo.getObsEventFieldConceptId(), 16);
		rtn += rpad(dvo.getObservationConceptId(), 16);
		rtn += rpad(this.getValueType(), 16);
		rtn += rpad(this.getValueAsString(), 64);
		rtn += rpad(this.getUnitsAsString(), 64);
		rtn += rpad(this.getName(), 64);
		return rtn;
	}

	private static String rpad(Object obj, int len) {
		String str = obj == null ? "" : obj.toString();
		if (str.length() >= len) {
			str = str.substring(0, (len - 4));
			str += "...";
		}
		return StringUtils.rightPad(str.toString(), len);
	}

}
