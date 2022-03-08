package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConceptCacheIntegrationTest {

	@Test
	public void cacheShouldKeepNewestValues() {
		log.info("Starting test..");
		ConceptCache cache = new ConceptCache();
		cache.setSize(3);
		ConceptDvo dvo1 = getDvo("system", "code1", 1);
		ConceptDvo dvo2 = getDvo("system", "code2", 2);
		ConceptDvo dvo3 = getDvo("system", "code3", 3);
		ConceptDvo dvo4 = getDvo("system", "code4", 4);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code1", dvo1);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code2", dvo2);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code3", dvo3);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code4", dvo4);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code3", dvo3);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code4", dvo4);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code3", dvo3);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code4", dvo4);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code3", dvo3);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		cache.add("system", "code4", dvo4);
		log.info("\n\n" + cache.getDebugString() + "\n\n");
		assertTrue(cache.getDebugString().indexOf("code1") < 0);
		assertTrue(cache.getDebugString().indexOf("code2") > 0);
		assertTrue(cache.getDebugString().indexOf("code3") > 0);
		assertTrue(cache.getDebugString().indexOf("code4") > 0);
		log.info("Done.");
	}
	
	private ConceptDvo getDvo(String system, String code, int id) {
		ConceptDvo dvo = new ConceptDvo();
		dvo.setConceptId(id);
		dvo.setVocabularyId(system);
		dvo.setConceptCode(code);
		return dvo;
	}
	
}
