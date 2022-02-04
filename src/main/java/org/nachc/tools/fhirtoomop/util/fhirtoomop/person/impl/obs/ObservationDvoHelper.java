package org.nachc.tools.fhirtoomop.util.fhirtoomop.person.impl.obs;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.ObservationDvo;
import org.yaorma.dao.Dao;

public class ObservationDvoHelper {

	//
	// instance variables
	//
	
	private ObservationDvo dvo;
	
	private ConceptDvo observationConceptDvo;

	//
	// constructor
	//
	
	public ObservationDvoHelper(ObservationDvo dvo, Connection conn) {
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

	//
	// implementation
	//

	public String getName() {
		try {
			return this.observationConceptDvo.getConceptName();
		} catch(Exception exp) {
			return null;
		}
	}
	
	public static String getFixedWithHeaderRow() {
		String rtn = "";
		rtn += pad("OBSERVATION_ID", 16);
		rtn += pad("OBS_CONCEPT_ID", 16);
		rtn += pad("OBS_NAME", 24);
		return rtn;
	}

	public String getAsFixedWidthString() {
		String rtn = "";
		rtn += pad(dvo.getObservationId(), 16);
		rtn += pad(dvo.getObservationConceptId(), 16);
		rtn += pad(this.getName(), 24);
		return rtn;
	}

	private static String pad(Object str, int len) {
		if (str == null) {
			str = "";
		}
		return StringUtils.rightPad(str.toString(), len);
	}

}
