package org.nachc.tools.fhirtoomop.omop.util.id;

import java.sql.Connection;
import java.util.HashMap;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGenerator {

	private static HashMap<String, Integer> keys = new HashMap<String, Integer>();

	private static final Object LOCK = new Object();

	public static Integer getId(String tableName, String idName, Connection conn) {
		synchronized (LOCK) {
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
	}

	public static Integer getIdFromDatabase(String tableName, String idName, Connection conn) {
		synchronized (LOCK) {
			String cdmDbType = AppParams.get("cdmDbType");
			if ("postgres".equals(cdmDbType)) {
				Database.commit(conn);
				String schemaName = AppParams.getDbName();
				String seqName = tableName + "_" + idName;
				seqName = schemaName + "." + seqName;
				String sqlString = "select nextval('" + seqName + "') as val";
				String str = Database.queryForFirst(sqlString, "val", conn);
				Integer rtn = Integer.parseInt(str);
				Database.commit(conn);
				return rtn;
			} else {
				String seqName = tableName + "_" + idName;
				String sqlString = "select next value for " + seqName + " as val";
				String str = Database.queryForFirst(sqlString, "val", conn);
				Integer rtn = Integer.parseInt(str);
				return rtn;
			}
		}
	}

	public static void invalidateKey(String tableName, String idName) {
		synchronized (LOCK) {
			String key = tableName + "." + idName;
			keys.remove(key);
		}
	}

	public static void invalidateAllKeys() {
		synchronized (LOCK) {
			keys = new HashMap<String, Integer>();
		}
	}

}
