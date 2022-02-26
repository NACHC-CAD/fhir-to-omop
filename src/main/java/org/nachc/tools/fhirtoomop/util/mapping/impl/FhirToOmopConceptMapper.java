package org.nachc.tools.fhirtoomop.util.mapping.impl;

import java.sql.Connection;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.ConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.system.SystemMapping;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopConceptMapper {

	//
	// passthrough method
	//

	public static ConceptDvo getOmopConceptForFhirCoding(Coding coding, Connection conn) {
		if (coding == null || coding.getCode() == null || coding.getSystem() == null) {
			return null;
		} else {
			String system = coding.getSystem();
			String code = coding.getCode();
			return getOmopConceptForFhirCoding(system, code, conn);
		}
	}

	public static ConceptDvo getOmopConceptForFhirCoding(String system, String code, Connection conn) {
		if (system == null || code == null) {
			return null;
		} else {
			ConceptDvo dvo = null;
			// look for concept in cache
			dvo = ConceptCache.get(system, code);
			if (dvo != null) {
				return dvo;
			}
			// look for a standard concept
			log.info("GETTING STANDARD: \t" + system + "\t" + code);
			dvo = getStandardConcept(system, code, conn);
			if (dvo != null) {
				ConceptCache.add(system, code, dvo);
				return dvo;
			}
			// look for a mapping to a standard concept
			log.info("GETTING MAPPED: \t" + system + "\t" + code);
			dvo = getStandardConceptFromMapping(system, code, conn);
			if(dvo != null) {
				ConceptCache.add(system, code, dvo);
			} else {
				ConceptDvo zeroDvo = new ConceptDvo();
				zeroDvo.setConceptId(0);
				ConceptCache.add(system, code, zeroDvo);
			}
			return dvo;
		}
	}

	private static ConceptDvo getStandardConcept(String system, String conceptCode, Connection conn) {
		if (system == null || conceptCode == null) {
			return null;
		} else {
			String sqlString = "select concept_id from concept where vocabulary_id = ? and concept_code = ? and standard_concept = 'S'";
			system = SystemMapping.getOmopSystemForFhirSystem(system);
			if (system == null) {
				return null;
			}
			String[] params = { system, conceptCode };
			List<ConceptDvo> list = Dao.findListBySql(new ConceptDvo(), sqlString, params, conn);
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}
	}

	private static ConceptDvo getStandardConceptFromMapping(String system, String conceptCode, Connection conn) {
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "	con2.* \n";
		sqlString += "from \n";
		sqlString += "	concept_relationship rel \n";
		sqlString += "    join concept con1 on con1.concept_id = rel.concept_id_1 \n";
		sqlString += "    join concept con2 on con2.concept_id = rel.concept_id_2 \n";
		sqlString += "where 1=1 \n";
		sqlString += "	and relationship_id = 'Maps to' \n";
		sqlString += "	and con1.vocabulary_id = ? \n";
		sqlString += "  and con1.concept_code = ? \n";
		sqlString += "	and con2.standard_concept = 'S' \n";
		system = SystemMapping.getOmopSystemForFhirSystem(system);
		String[] params = { system, conceptCode };
		log.debug("\n\n" + sqlString + "\n\n");
		log.debug("Getting concept...");
		log.debug(system + "\t" + conceptCode);
		List<ConceptDvo> list = Dao.findListBySql(new ConceptDvo(), sqlString, params, conn);
		log.debug("GOT IT");
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
