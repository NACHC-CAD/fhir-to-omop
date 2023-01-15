package org.nachc.tools.fhirtoomop.omop.util.id;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGenerator {

	private static final Object LOCK = new Object();

	private static volatile HashMap<String, Integer> KEYS = new HashMap<String, Integer>();

	public static Integer getId(String tableName, String idName) {
		synchronized (LOCK) {
			String keyName = tableName + "." + idName;
			Integer key = KEYS.get(keyName);
			if (key == null) {
				key = 1;
			} else {
				key++;
			}
			KEYS.put(keyName, key);
			return key;
		}
	}

}
