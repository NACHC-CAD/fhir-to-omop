package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;

public class ConceptCache {

	private static final int SIZE = 10000;

	private static HashMap<CacheKey, ConceptDvo> concepts = new HashMap<CacheKey, ConceptDvo>();

	private static Queue<CacheKey> queue = new LinkedList<CacheKey>();

	public static ConceptDvo get(String system, String code) {
		if (system == null || code == null) {
			return null;
		} else {
			CacheKey key = new CacheKey(system, code);
			ConceptDvo rtn = concepts.get(key);
			concepts.keySet().contains(key);
			return rtn;
		}
	}

	public static void add(String system, String code, ConceptDvo dvo) {
		if (concepts.size() >= SIZE) {
			CacheKey key = queue.poll();
			concepts.remove(key);
		}
		CacheKey key = new CacheKey(system, code);
		concepts.put(key, dvo);
		queue.add(key);
	}

}
