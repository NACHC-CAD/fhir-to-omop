package org.nachc.tools.fhirtoomop.util.mapping;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.nachc.tools.omop.yaorma.dvo.FhirToOmopEthnicityDvo;
import org.yaorma.dao.Dao;

public class EthnicityMapping {

	private Connection conn;

	public EthnicityMapping(Connection conn) {
		this.conn = conn;
	}

	public ConceptDvo getOmopConceptForFhirCode(String code) {
		FhirToOmopEthnicityDvo mapping = Dao.find(new FhirToOmopEthnicityDvo(), "code", code, this.conn);
		if (mapping != null) {
			Integer omopId = mapping.getOmopCode();
			if (omopId != null) {
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
