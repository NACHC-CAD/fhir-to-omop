package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.nachc.tools.omop.yaorma.dvo.ConceptDvo;

public class ConceptCache {

	private static int SIZE = 100000;

	private static HashMap<CacheKey, ConceptDvo> concepts = new HashMap<CacheKey, ConceptDvo>();

	private static Queue<CacheKey> queue = new LinkedList<CacheKey>();

	// TODO: Is this going to cause problems? (JEG)
	public static void setSize(int size) {
		SIZE = size;
	}
	
	public static int getSize() {
		return SIZE;
	}
	
	public static synchronized ConceptDvo get(String system, String code) {
		if (system == null || code == null) {
			return null;
		} else {
			CacheKey key = new CacheKey(system, code);
			ConceptDvo rtn = concepts.get(key);
			concepts.keySet().contains(key);
			return rtn;
		}
	}

	public static synchronized void add(String system, String code, ConceptDvo dvo) {
		CacheKey key = new CacheKey(system, code);
		if (concepts.size() >= SIZE) {
			if(queue.contains(key)) {
				queue.remove(key);
			} else {
				CacheKey first = queue.poll();
				concepts.remove(first);
			}
		}
		concepts.put(key, dvo);
		queue.add(key);
	}

	public static String getDebugString() {
		String msg = "";
		msg += "id\tsystem\tcode\n";
		Iterator<CacheKey> itr = queue.iterator();
		while(itr.hasNext()) {
			CacheKey key = itr.next();
			ConceptDvo dvo = concepts.get(key);
			msg += dvo.getConceptId() + "\t" + dvo.getVocabularyId() + "\t" + dvo.getConceptCode() + "\n";
		}
		return msg;
	}
}
