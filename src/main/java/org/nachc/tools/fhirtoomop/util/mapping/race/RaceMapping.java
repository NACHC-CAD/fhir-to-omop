package org.nachc.tools.fhirtoomop.util.mapping.race;

import java.sql.Connection;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.FhirToOmopRaceDvo;
import org.yaorma.dao.Dao;

public class RaceMapping {

	private Connection conn;

	public RaceMapping(Connection conn) {
		this.conn = conn;
	}

	public ConceptDvo getOmopConceptForFhirCode(String code) {
		FhirToOmopRaceDvo mapping = Dao.find(new FhirToOmopRaceDvo(), "code", code, this.conn);
		if(mapping != null) {
			Integer omopId = mapping.getOmopCode();
			if(omopId != null) {
				ConceptDvo rtn = Dao.find(new ConceptDvo(), "concept_id", omopId + "", conn);
				return rtn;
			}
		}
		return null;
	}

}
