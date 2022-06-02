package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StandardConceptCache {

	private static HashMap<CacheKey, ConceptDvo> concepts;

	public static void init(Connection conn) {
		ResultSet rs = null;
		try {
			log.info("CREATING STANDARD CONCEPT CACHE...");
			if (concepts == null) {
				concepts =  new HashMap<CacheKey, ConceptDvo>();
				log.info("Querying database...");
				String sqlString = "select * from concept where standard_concept = 'S' and vocabulary_id in('SNOMED', 'RxNorm', 'LOINC', 'UCUM')";
				rs = Database.executeQuery(sqlString, conn);
				log.info("Creating cache...");
				int cnt = 0;
				while (rs.next()) {
					cnt++;
					ConceptDvo dvo = new ConceptDvo();
					Dao.load(dvo, rs);
					CacheKey key = new CacheKey(dvo.getVocabularyId(), dvo.getConceptCode());
					concepts.put(key, dvo);
					if(cnt % 100000 == 0) {
						log.info("Got " + cnt + " concepts");
					}
				}
				log.info("Got result set...");
			} else {
				log.info("Concept cache has already been initialted: (" + concepts.size() + ") standard concepts.");
			}
		} catch(Exception exp) {
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
