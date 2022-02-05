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
	
	private ObservationValueType valueType;

	//
	// constructor
	//

	public ObservationDvoProxy(ObservationDvo dvo, Connection conn) {
		this.dvo = dvo;
		this.observationConceptDvo = Dao.find(new ConceptDvo(), "concept_id", dvo.getObservationConceptId() + "", conn);
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

	//
	// method to get value type
	//



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
		rtn += rpad("OBS_NAME", 24);
		return rtn;
	}

	public String getAsFixedWidthString() {
		String rtn = "";
		rtn += rpad(dvo.getObservationId(), 16);
		rtn += rpad(dvo.getObservationConceptId(), 16);
		rtn += rpad(this.getValueType(), 16);
		rtn += rpad(this.getName(), 24);
		return rtn;
	}

	private static String rpad(Object str, int len) {
		if (str == null) {
			str = "";
		}
		return StringUtils.rightPad(str.toString(), len);
	}

}
