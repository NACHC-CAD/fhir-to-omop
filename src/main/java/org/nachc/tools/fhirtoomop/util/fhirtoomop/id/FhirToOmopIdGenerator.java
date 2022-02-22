package org.nachc.tools.fhirtoomop.util.fhirtoomop.id;

import java.sql.Connection;
import java.util.HashMap;

import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGenerator {

	private static HashMap<String, Integer> keys = new HashMap<String, Integer>();

	public synchronized static Integer getId(String tableName, String idName, Connection conn) {
		String keyName = tableName + "." + idName;
		Integer key = keys.get(keyName);
		if (key == null) {
			key = getIdFromDatabase(tableName, idName, conn);
		} else {
			key++;
		}
		keys.put(keyName, key);
		return key;
	}

	public static Integer getIdFromDatabase(String tableName, String idName, Connection conn) {
		String sqlString = "select max(" + idName + ") as id from " + tableName;
		Data data = Database.query(sqlString, conn);
		if (data.size() == 0) {
			return 1;
		} else {
			Integer rtn = data.get(0).getInt("id");
			if (rtn == null) {
				return 1;
			} else {
				return rtn + 1;
			}
		}
	}

	public static void invalidateKey(String tableName, String idName) {
		String key = tableName + "." + idName;
		keys.remove(key);
	}
	
	public static void invalidateAllKeys() {
		keys = new HashMap<String, Integer>();
	}
	
}
