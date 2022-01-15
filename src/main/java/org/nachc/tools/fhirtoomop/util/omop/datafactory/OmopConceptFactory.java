package org.nachc.tools.fhirtoomop.util.omop.datafactory;

import java.sql.Connection;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;

public class OmopConceptFactory {

	public static ConceptDvo getConcept(Integer id, Connection conn) {
		if(id == null) {
			return null;
		}
		ConceptDvo rtn = Dao.find(new ConceptDvo(), "concept_id", id + "", conn);
		return rtn;
	}
	
}
