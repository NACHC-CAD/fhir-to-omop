package org.nachc.tools.fhirtoomop.util.mapping.impl;

import java.sql.Connection;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.mapping.system.SystemMapping;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;

public class FhirToOmopConceptMapper {

	public static ConceptDvo getOmopConceptForFhirCoding(Coding coding, Connection conn) {
		ConceptDvo dvo = null;
		dvo = getStandardConcept(coding, conn);
		return dvo;
	}

	private static ConceptDvo getStandardConcept(Coding coding, Connection conn) {
		if (coding == null || coding.getCode() == null || coding.getSystem() == null) {
			return null;
		} else {
			String sqlString = "select * from concept where vocabulary_id = ? and concept_code = ? and standard_concept = 'S'";
			String system = coding.getSystem();
			system = SystemMapping.getOmopSystemForFhirSystem(system);
			if (system == null) {
				return null;
			}
			String conceptCode = coding.getCode();
			String[] params = { system, conceptCode };
			List<ConceptDvo> list = Dao.findListBySql(new ConceptDvo(), sqlString, params, conn);
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}
	}

}
