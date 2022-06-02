package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MappedConceptCache {

	private static HashMap<CacheKey, ConceptDvo> concepts;

	public static void init(Connection conn) {
		ResultSet rs = null;
		try {
			log.info("CREATING MAPPED CONCEPT CACHE...");
			if (concepts == null) {
				concepts = new HashMap<CacheKey, ConceptDvo>();
				log.info("Querying database...");
				String sqlString = getSqlString();
				rs = Database.executeQuery(sqlString, conn);
				log.info("Creating cache...");
				int cnt = 0;
				while (rs.next()) {
					cnt++;
					ConceptDvo dvo = new ConceptDvo();
					Dao.load(dvo, rs);
					CacheKey key = new CacheKey(dvo.getVocabularyId(), dvo.getConceptCode());
					concepts.put(key, dvo);
					if (cnt % 100000 == 0) {
						log.info("Got " + cnt + " concepts");
					}
				}
				log.info("Got result set...");
			} else {
				log.info("Concept cache has already been initialted: (" + concepts.size() + ") standard concepts.");
			}
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception exp) {
				throw new RuntimeException(exp);
			}
		}
	}

	private static String getSqlString() {
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "  con2.* \n";
		sqlString += "from \n";
		sqlString += "  concept_relationship rel \n";
		sqlString += "    join concept con1 on con1.concept_id = rel.concept_id_1 \n";
		sqlString += "    join concept con2 on con2.concept_id = rel.concept_id_2 \n";
		sqlString += "where 1=1 \n";
		sqlString += "  and relationship_id = 'Maps to' \n";
		sqlString += "  and con2.standard_concept = 'S' \n";
		sqlString += "  and con1.vocabulary_id in('SNOMED', 'RxNorm', 'LOINC', 'UCUM')";
		return sqlString;
	}
	
	public static ConceptDvo get(String omopVocabularyId, String code) {
		if(concepts == null) { 
			return null;
		} else {
			CacheKey key = new CacheKey(omopVocabularyId, code);
			ConceptDvo rtn = concepts.get(key);
			return rtn;
		}
	}

}
