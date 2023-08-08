package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBR08_DeleteAchillesWebApiRecords {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksWebApiConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String key = DatabricksProperties.getWebApiKey();
		log.info("DELETING WEBAPI RECORDS FOR: " + key);
		String sourceId = getSourceId(key, conn);
		log.info("Deleteing records for key(sourceId): " + key + " (" + sourceId + ")");
		deleteRecords(key, sourceId, conn);
		log.info("Done with delete.");
	}

	private static String getSourceId(String key, Connection conn) {
		String schema = DatabricksProperties.getWebApiSchema();
		String sqlString = "select source_id from " + schema + "." + "source where source_key = ?";
		log.info("sqlString\n" + sqlString);
		Data data = Database.query(sqlString, key, conn);
		if (data.size() > 0) {
			String id = data.get(0).get("sourceId");
			log.info("Got id: " + id);
			return id;
		} else {
			return null;
		}
	}

	private static void deleteRecords(String key, String sourceId, Connection conn) {
		if(sourceId != null) {
			int sourceDaimonCnt = deleteSourceDaimonRecords(sourceId, conn);
			int sourceCnt = deleteSourceRecords(sourceId, conn);
			log.info("--------");
			log.info(sourceDaimonCnt + " records deleted from source_daimon.");
			log.info(sourceCnt + " records deleted from source.");
			log.info("--------");
		} else {
			log.info("No records found (delete skipped, nothing to delete) for key: " + key);
			log.info("0 records deleted from source_daimon.");
			log.info("0 records deleted from source.");
		}
	}

	private static int deleteSourceDaimonRecords(String sourceId, Connection conn) {
		log.info("Deleting source_daimon records...");
		String schema = DatabricksProperties.getWebApiSchema();
		String sqlString = "delete from " + schema + "." + "source_daimon where source_id = " + sourceId;
		log.info("delete source_daimon sqlString: \n" + sqlString);
		int cnt = Database.update(sqlString, conn);
		return cnt;
	}

	private static int deleteSourceRecords(String sourceId, Connection conn) {
		log.info("Deleteing source records...");
		String schema = DatabricksProperties.getWebApiSchema();
		String sqlString = "delete from " + schema + "." + "source where source_id = " + sourceId;
		log.info("delete source_daimon sqlString: \n" + sqlString);
		int cnt = Database.update(sqlString, conn);
		return cnt;
	}
	
}
