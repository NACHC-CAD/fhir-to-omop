package org.nachc.tools.fhirtoomop.util.mapping.impl;

import java.sql.Connection;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.nachc.tools.fhirtoomop.omop.util.id.FhirToOmopIdGenerator;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.ConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.MappedConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.StandardConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.system.SystemMapping;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopConceptMapper {

	private static Object lock = new Object();

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
		synchronized (lock) {
			if (system == null || code == null) {
				return null;
			} else {
				ConceptDvo dvo = null;
				// look for concept in standard concept cacche
				String omopVocabularyId = SystemMapping.getOmopSystemForFhirSystem(system);
				dvo = StandardConceptCache.get(omopVocabularyId, code);
				if (dvo != null) {
					return dvo;
				}
				// look for concept in mapped concept cache
				dvo = MappedConceptCache.get(omopVocabularyId, code);
				if (dvo != null) {
					return dvo;
				}
				// look for concept in cache
				dvo = ConceptCache.ACTIVE_CACHE.get(system, code);
				if (dvo != null) {
					return dvo;
				}
				// look for a standard concept
				dvo = getStandardConcept(system, code, conn);
				if (dvo != null) {
					ConceptCache.ACTIVE_CACHE.add(system, code, dvo);
					return dvo;
				}
				// look for a mapping to a standard concept
				dvo = getStandardConceptFromMapping(system, code, conn);
				if (dvo != null) {
					ConceptCache.ACTIVE_CACHE.add(system, code, dvo);
					return dvo;
				}
				// look for a non-standard concept
				dvo = getNonStandardConcept(system, code, conn);
				if (dvo != null) {
					ConceptCache.ACTIVE_CACHE.add(system, code, dvo);
					return dvo;
				}
				// create a new concept with id > 1B
				ConceptDvo newConceptDvo = addTempConcept(system, code, conn);
				ConceptCache.ACTIVE_CACHE.add(system, code, newConceptDvo);
				return newConceptDvo;
			}
		}
	}

	private static ConceptDvo getStandardConcept(String system, String conceptCode, Connection conn) {
		if (system == null || conceptCode == null) {
			return null;
		} else {
			ConceptDvo dvoFromCache = ConceptCache.ACTIVE_CACHE.get(system, conceptCode);
			if (dvoFromCache != null) {
				return dvoFromCache;
			}
			log.info("looking for standard concept: " + system + "\t" + conceptCode);
			String sqlString = "select * from concept where vocabulary_id = ? and concept_code = ? and standard_concept = 'S'";
			system = SystemMapping.getOmopSystemForFhirSystem(system);
			if (system == null) {
				return null;
			}
			String[] params = { system, conceptCode };
			List<ConceptDvo> list = Dao.findListBySql(new ConceptDvo(), sqlString, params, conn);
			log.info("Got " + list.size() + " concepts");
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}
	}

	private static ConceptDvo getStandardConceptFromMapping(String system, String conceptCode, Connection conn) {
		ConceptDvo dvoFromCache = ConceptCache.ACTIVE_CACHE.get(system, conceptCode);
		if (dvoFromCache != null) {
			return dvoFromCache;
		}
		log.info("looking for mapped concept: " + system + "\t" + conceptCode);
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

	private static ConceptDvo getNonStandardConcept(String system, String conceptCode, Connection conn) {
		ConceptDvo dvoFromCache = ConceptCache.ACTIVE_CACHE.get(system, conceptCode);
		if (dvoFromCache != null) {
			return dvoFromCache;
		}
		log.info("looking for non-standard concept: " + system + "\t" + conceptCode);
		if (system == null || conceptCode == null) {
			return null;
		} else {
			String sqlString = "select * from concept where vocabulary_id = ? and concept_code = ?";
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

	private static ConceptDvo addTempConcept(String system, String code, Connection conn) {
		ConceptDvo rtn = doInsertOfNewConcept(system, code);
		Database.commit(conn);
		return rtn;
	}

	private static ConceptDvo doInsertOfNewConcept(String system, String code) {
		ConceptDvo rtn = null;
		log.info("GETTING CONNECTION...");
		Connection conn = null;
		if("postgres".equals(AppParams.get("cdmDbType"))) {
			conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		} else {
			conn = OmopDatabaseConnectionFactory.getOmopConnection();
		}
		log.info("GOT CONNECTION.");
		try {
			log.info("Maybe adding a new concept...");
			Database.update("begin transaction", conn);
//			int secsToSleep = 3;
//			log.info("Waiting " + secsToSleep + " seconds for existing threads to finish or get to this lock...");
//			TimeUtil.sleep(secsToSleep);
			rtn = findExistingTempConcept(system, code, conn);
			if (rtn == null) {
				log.info("ADDING NEW CONCEPT: " + system + " (" + code + ")");
				rtn = addTempConceptTransaction(system, code, conn);
				Database.update("commit transaction", conn);
				Database.commit(conn);
			} else {
				log.info("#############################");
				log.info("FOUND EXISTING TEMP CONCEPT: " + rtn.getVocabularyId() + "(" + rtn.getConceptCode() + ")");
				log.info("#############################");
			}
			return rtn;
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}

	}

	private static ConceptDvo findExistingTempConcept(String system, String code, Connection conn) {
		String vocabularyId = SystemMapping.getOmopSystemForFhirSystem(system);
		String sqlString = "select * from concept where vocabulary_id = ? and concept_code = ? and concept_id > 2000000000";
		String[] params = { vocabularyId, code };
		Database.query(sqlString, params, conn);
		List<ConceptDvo> data = Dao.findListBySql(new ConceptDvo(), sqlString, params, conn);
		if (data.size() > 0) {
			return data.get(0);
		} else {
			return null;
		}
	}

	private static ConceptDvo addTempConceptTransaction(String system, String code, Connection conn) {
		String vocabularyId = SystemMapping.getOmopSystemForFhirSystem(system);
		Integer id = FhirToOmopIdGenerator.getId("concept", "concept_id", false);
		ConceptDvo dvo = new ConceptDvo();
		dvo.setConceptId(id);
		dvo.setVocabularyId(vocabularyId);
		dvo.setConceptCode(code);
		// TODO: FIX THIS (This is a place holder for now)
		dvo.setDomainId("Measurement");
		dvo.setConceptClassId("Clinical Observation");
		dvo.setConceptName(code);
		dvo.setValidStartDate(TimeUtil.getDateForYyyy_Mm_Dd("1770-12-17"));
		dvo.setValidEndDate(TimeUtil.getDateForYyyy_Mm_Dd("2222-01-01"));
		Dao.insert(dvo, conn);
		log.info("+++++++++++++++++++++");
		log.info("New concept created " + dvo.getConceptId() + ": (" + vocabularyId + "): " + code + "\t");
		log.info("+++++++++++++++++++++");
		return dvo;
	}


}
