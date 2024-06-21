package org.nachc.tools.fhirtoomop.util.webapi;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteWebApiRecords {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done with exec().");
	}
	
	public static void exec(Connection conn) {
		String schemaName = getSchemaName();
		String key = AppParams.get("atlasDataSourceKey");
		log.info("Source key: " + key);
		String sourceId = getSourceId(conn);
		log.info("Source Id: " + sourceId);
		if(sourceId != null) {
			log.info("Deleting records...");
			deleteSourceDaimon(conn, schemaName, sourceId);
			deleteSource(conn, schemaName, sourceId);
		} else {
			log.info("No records for source_key, skipping delete: " + key);
		}
		log.info("Done deleting records.");
	}

	private static void deleteSourceDaimon(Connection conn, String schemaName, String sourceId) {
		log.info("Deleting source_daimon record...");
		String tableName = schemaName + "." + "source_daimon";
		String sqlString = "";
		sqlString += "delete from " + tableName + " ";
		sqlString += "where source_id = '" + sourceId + "'"; 
		Database.update(sqlString, conn);
	}
	
	private static void deleteSource(Connection conn, String schemaName, String sourceId) {
		log.info("Deleting source record...");
		String tableName = schemaName + "." + "source";
		String sqlString = "";
		sqlString += "delete from " + tableName + " ";
		sqlString += "where source_id = " + sourceId; 
		Database.update(sqlString, conn);
	}
	
	private static String getSchemaName() {
		String dbName = AppParams.get("postgresWebApiDatabaseName");
		String schemaName = AppParams.get("postgresWebApiSchemaName");
		String rtn = dbName + "." + schemaName;
		return rtn;
	}

	private static String getSourceId(Connection conn) {
		String sourceKey = AppParams.get("atlasDataSourceKey");
		String schemaName = getSchemaName();
		String tableName = schemaName + ".source";
		String sqlString = "";
		sqlString += "select source_id from " + tableName + " ";
		sqlString += "where source_key = '" + sourceKey + "'";
		Data data = Database.query(sqlString, conn);
		String rtn = null;
		if (data.size() > 0) {
			rtn = data.get(0).get("sourceId");
		}
		return rtn;
	}

}
