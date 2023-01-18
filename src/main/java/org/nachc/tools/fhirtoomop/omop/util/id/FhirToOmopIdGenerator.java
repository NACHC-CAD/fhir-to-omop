package org.nachc.tools.fhirtoomop.omop.util.id;

import java.sql.Connection;
import java.util.HashMap;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopIdGenerator {

	private static final Object LOCK = new Object();

	private static final Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
	
	private static volatile HashMap<String, Integer> KEYS = new HashMap<String, Integer>();

	public static Integer getId(String tableName, String idName) {
		synchronized (LOCK) {
			return getId(tableName, idName, true);
		}
	}

	public static Integer getId(String tableName, String idName, boolean useCache) {
		synchronized (LOCK) {
			String keyName = tableName + "." + idName;
			Integer key = KEYS.get(keyName);
			if (key == null || useCache == false) {
				key = getIdFromDatabase(tableName, idName);
			} else {
				key++;
			}
			KEYS.put(keyName, key);
			return key;
		}
	}

	private static Integer getIdFromDatabase(String tableName, String idName) {
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

}
