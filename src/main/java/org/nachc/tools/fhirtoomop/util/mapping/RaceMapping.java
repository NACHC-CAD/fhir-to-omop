package org.nachc.tools.fhirtoomop.util.mapping;

import java.sql.Connection;
import java.util.List;

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
				String sqlString = "select * from concept where concept_id = " + omopId;
				List<ConceptDvo> list = Dao.findListBySql(new ConceptDvo(), sqlString, conn);
				if(list.size() > 0) {
					return list.get(0);
				} else {
					return null;
				}
			}
		}
		return null;
	}

}
