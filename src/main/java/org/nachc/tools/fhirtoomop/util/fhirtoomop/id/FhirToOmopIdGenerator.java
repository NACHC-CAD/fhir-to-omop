package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import java.sql.Connection;
import java.util.HashMap;

public class FhirToOmopIdGenerator {

	// TODO: (JEG) Keeping this simple for now is something more robust is not
	// required for our use cases.

	private static HashMap<String, Integer> keys = new HashMap<String, Integer>();

	public synchronized static Integer getId(String tableName, String idName, Connection conn) {
		String keyName = tableName + "." + idName;
		Integer key = keys.get(keyName);
		if (key == null) {
			key = 1;
		} else {
			key++;
		}
		keys.put(keyName, key);
		return key;
	}

}
