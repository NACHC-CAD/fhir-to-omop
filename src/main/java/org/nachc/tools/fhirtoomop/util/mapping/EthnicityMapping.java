package org.nachc.tools.fhirtoomop.util.mapping;

import java.sql.Connection;

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
				ConceptDvo rtn = Dao.find(new ConceptDvo(), "concept_id", omopId + "", conn);
				return rtn;
			}
		}
		return null;
	}

}
