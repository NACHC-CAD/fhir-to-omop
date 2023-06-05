package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A09_CreateAchillesWebApiRecords {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksWebApiConnectionFactory.getConnection();
			// delete existing records
			log.info("DELETEING EXISTING RECORDS...");
			A08_DeleteAchillesWebApiRecords.exec(conn);
			// create new records
			log.info("CREATING NEW RECORDS...");
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String sourceId = getSourceId(conn);
		insertSource(conn, sourceId);
	}
	
	private static void insertSource(Connection conn, String sourceId) {
		log.info("Inserting source record...");
		String sqlString = FileUtil.getAsString("/databricks/webapi/insert-webapi-src.sql");
		String sourceName = DatabricksProperties.getWebApiName();
		String sourceKey = DatabricksProperties.getWebApiKey();
		String sourceConnection = getSourceConnection();
		sqlString = sqlString.replace("@sourceId", sourceId);
		sqlString = sqlString.replace("@sourceName", sourceName);
		sqlString = sqlString.replace("@sourceKey", sourceKey);
		sqlString = sqlString.replace("@sourceConnection", sourceConnection);
		log.info("Executing script: \n" + sqlString);
		Database.executeSqlScript(sqlString, conn);
		log.info("Done inserting source record.");
	}

	//
	// private method to get the nextVal for the primary key for the source table
	//
	
	private static String getSourceId(Connection conn) {
		String sqlString = "select nextval('webapi.source_daimon_sequence') as nextval";
		Data data = Database.query(sqlString, conn);
		if(data.size() == 0) {
			throw new RuntimeException("Could not get nextVal:\n" + sqlString);
		}
		String rtn = data.get(0).get("nextval");
		return rtn;
	}

	//
	// private method to get the url
	//
	
	private static String getSourceConnection() {
		String rtn = DatabricksProperties.getJdbcUrl();
		if(rtn.indexOf(";ssl=") < 0) {
			throw new RuntimeException("Bad url, ssl must be defined (i.e. ;ssl=0 or ;ssl=1 needs to be included in the url) for: \n" + rtn);
		}
		if(rtn.indexOf("UseNativeQuery") < 0) {
			rtn = rtn.replace(";ssl=", ";UseNativeQuery=1;ssl=");
		}
		return rtn;
	}

}
