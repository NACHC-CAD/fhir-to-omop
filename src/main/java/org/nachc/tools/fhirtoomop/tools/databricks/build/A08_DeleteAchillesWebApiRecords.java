package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.WebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A08_DeleteAchillesWebApiRecords {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = WebApiConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}
	
	public static void exec(Connection conn) {
		String key = DatabricksProperties.getWebApiKey();
		log.info("DELETING WEBAPI RECORDS FOR: " + key);
		String sourceId = getSourceId(key, conn);
	}

	private static String getSourceId(String key, Connection conn) {
		String schema = DatabricksProperties.getWebApiSchema();
		String sqlString = "select source_id from " + schema + "." + "source where source_key = ?";
		Data data = Database.query(sqlString, key, conn);
		if(data.size() > 0) {
			String id = data.get(0).get("sourceId");
			log.info("Got id: " + id);
			return id;
		} else {
			return null;
		}
	}

}
