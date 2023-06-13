package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/* * * *
 * 
 * Documentation for this is at:
 * https://github.com/OHDSI/WebAPI/wiki/CDM-Configuration
 * 
 * * * */

@Slf4j
public class DBR09_CreateAchillesWebApiRecords {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksWebApiConnectionFactory.getConnection();
			// delete existing records
			log.info("DELETEING EXISTING RECORDS...");
			DBR08_DeleteAchillesWebApiRecords.exec(conn);
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
		insertSourceDaimon(conn, sourceId);
	}

	private static void insertSource(Connection conn, String sourceId) {
		log.info("Inserting source record...");
		String sqlString = null;
		String webApiSchemaName = DatabricksProperties.getWebApiSchema();
		if (hasIsCacheEnabledCol(webApiSchemaName, conn)) {
			sqlString = FileUtil.getAsString("/databricks/webapi/insert-webapi-src-cache-enabled.sql");
		} else {
			sqlString = FileUtil.getAsString("/databricks/webapi/insert-webapi-src.sql");
		}
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

	private static void insertSourceDaimon(Connection conn, String sourceId) {
		// build the sqlString
		log.info("Inserting source_daimon records...");
		String sqlString = FileUtil.getAsString("/databricks/webapi/insert-webapi-src-daimon.sql");
		String webApiSchemaName = DatabricksProperties.getWebApiSchema();
		String cdmSchemaName = DatabricksProperties.getSchemaName();
		String vocabSchemaName = DatabricksProperties.getVocabSchemaName();
		String achillesResultsSchemaName = DatabricksProperties.getAchillesResultsSchemaName();
		sqlString = sqlString.replace("@webApiSchemaName", webApiSchemaName);
		sqlString = sqlString.replace("@sourceId", sourceId);
		sqlString = sqlString.replace("@cdmSchemaName", cdmSchemaName);
		sqlString = sqlString.replace("@vocabSchemaName", vocabSchemaName);
		sqlString = sqlString.replace("@achillesResultsSchemaName", achillesResultsSchemaName);
		sqlString = sqlString.replace("@sourceDaimonId01", getSourceDaimonId(conn));
		sqlString = sqlString.replace("@sourceDaimonId02", getSourceDaimonId(conn));
		sqlString = sqlString.replace("@sourceDaimonId03", getSourceDaimonId(conn));
		// log the sqlString
		log.info("SQL FOR SOURCE_DAIMON INSERTS: \n" + sqlString);
		// execute the sqlString
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating source_daimon records");
	}

	//
	// private method to get the nextVal for the primary key for the source table
	//

	private static String getSourceId(Connection conn) {
		String sqlString = "select nextval('webapi.source_sequence') as nextval";
		Data data = Database.query(sqlString, conn);
		if (data.size() == 0) {
			throw new RuntimeException("Could not get nextVal:\n" + sqlString);
		}
		String rtn = data.get(0).get("nextval");
		return rtn;
	}

	private static String getSourceDaimonId(Connection conn) {
		String sqlString = "select nextval('webapi.source_daimon_sequence') as nextval";
		Data data = Database.query(sqlString, conn);
		if (data.size() == 0) {
			throw new RuntimeException("Could not get nextVal:\n" + sqlString);
		}
		String rtn = data.get(0).get("nextval");
		return rtn;
	}

	//
	// private method to get the url
	//

	private static String getSourceConnection() {
		String url = DatabricksProperties.getJdbcUrl();
		String token = DatabricksProperties.getToken();
		if (url.indexOf(";ssl=") < 0) {
			throw new RuntimeException("Bad url, ssl must be defined (i.e. ;ssl=0 or ;ssl=1 needs to be included in the url) for: \n" + url);
		}
		if (url.indexOf("UseNativeQuery") < 0) {
			url = url.replace(";ssl=", ";UseNativeQuery=1;ssl=");
		}
		url = url + token;
		return url;
	}

	private static boolean hasIsCacheEnabledCol(String webApiSchemaName, Connection conn) {
		try {
			String sqlString = "select is_cache_enabled from " + webApiSchemaName + "." + "source";
			log.info("Checking for is_cache_enable column: \n" + sqlString);
			Database.query(sqlString, conn);
			log.info("IS_CACHE_ENABLED detected");
			return true;
		} catch (Exception exp) {
			log.info("IS_CACHE_ENABLED NOT detected");
			return false;
		}
	}

}
