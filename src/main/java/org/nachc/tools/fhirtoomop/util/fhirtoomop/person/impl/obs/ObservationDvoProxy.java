package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.yaorma.dao.Dao;

public class ObservationDvoProxy {

	//
	// instance variables
	//

	private ObservationDvo dvo;

	private ConceptDvo observationConceptDvo;
	
	private ConceptDvo valueConceptDvo;
	
	private ConceptDvo valueUnitsConceptDvo;
	
	private ObservationValueType valueType;

	//
	// constructor
	//

	public ObservationDvoProxy(ObservationDvo dvo, Connection conn) {
		this.dvo = dvo;
		this.observationConceptDvo = getConceptDvo(dvo.getObservationConceptId(), conn);
		this.valueConceptDvo = getConceptDvo(dvo.getValueAsConceptId(), conn);
		this.valueUnitsConceptDvo = getConceptDvo(dvo.getUnitConceptId(), conn);
	}

	private ConceptDvo getConceptDvo(Integer conceptId, Connection conn) {
		if(conceptId == null) {
			return null;
		} else {
			return  Dao.find(new ConceptDvo(), "concept_id", conceptId + "", conn);
		}
	}
	
	//
	// trivial getters
	//

	public ObservationDvo getDvo() {
		return dvo;
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

	//
	// trivial setters
	//
	
	public void setObservationValueType(ObservationValueType valueType) {
		this.valueType = valueType;
	}
	
	//
	// implementation
	//

	public String getName() {
		try {
			return this.observationConceptDvo.getConceptName();
		} catch (Exception exp) {
			return null;
		}
	}

	public String getUnitsAsString() {
		if(this.valueUnitsConceptDvo != null) {
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
		if(this.getValueType() == ObservationValueType.CODED) {
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
		rtn += rpad("OBSERVATION_ID", 16);
		rtn += rpad("OBS_CONCEPT_ID", 16);
		rtn += rpad("VALUE_TYPE", 16);
		rtn += rpad("VALUE_AS_STRING", 64);
		rtn += rpad("UNITS", 64);
		rtn += rpad("OBS_NAME", 64);
		return rtn;
	}

	public String getAsFixedWidthString() {
		String rtn = "";
		rtn += rpad(dvo.getObservationId(), 16);
		rtn += rpad(dvo.getObservationConceptId(), 16);
		rtn += rpad(this.getValueType(), 16);
		rtn += rpad(this.getValueAsString(), 64);
		rtn += rpad(this.getUnitsAsString(), 64);
		rtn += rpad(this.getName(), 64);
		return rtn;
	}

	private static String rpad(Object obj, int len) {
		String str = obj == null? "" : obj.toString();
		if(str.length() > len) {
			str = str.substring(0, (len - 4));
			str += "...";
		}
		return StringUtils.rightPad(str.toString(), len);
	}

}
