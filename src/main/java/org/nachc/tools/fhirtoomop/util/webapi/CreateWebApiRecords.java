package org.nachc.tools.fhirtoomop.util.webapi;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateWebApiRecords {

	private static final String FILE_PATH = "/achilles/create-webapi-source-records.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Getting POSTGRESQL connection...");
		Connection conn = getConnection();
		log.info("Got connection.");
		try {
			deleteWebApiRecords(conn);
			createRecords(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating webapi records.");
	}

	private static Connection getConnection() {
		return PostgresDatabaseConnectionFactory.getBootstrapConnection();
	}

	public static void deleteWebApiRecords(Connection conn) {
		DeleteWebApiRecords.exec(conn);
	}

	private static void createRecords(Connection conn) {
		String sqlString = getSqlString();
		log.info("SqlString: \n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done creating records.");		
	}

	private static String getSourceId(Connection conn) {
		String sourceKey = AppParams.get("atlasDataSourceKey");
		String tableName = getTableName();
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

	private static String getTableName() {
		String dbName = AppParams.get("postgresWebApiDatabaseName");
		String schemaName = AppParams.get("postgresWebApiSchemaName");
		String tableName = dbName + "." + schemaName + ".source";
		return tableName;
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<atlasDataSourceName>", AppParams.get("atlasDataSourceName"));
		sqlString = sqlString.replace("<atlasDataSourceKey>", AppParams.get("atlasDataSourceKey"));
		sqlString = sqlString.replace("<atlasCdmUrl>", AppParams.get("atlasCdmUrl"));
		sqlString = sqlString.replace("<atlasResults>", AppParams.get("atlasResults"));
		sqlString = sqlString.replace("<atlasTemp>", AppParams.get("atlasTemp"));
		sqlString = sqlString.replace("<atlasCdm>", AppParams.get("atlasCdm"));
		sqlString = sqlString.replace("<atlasDbms>", AppParams.get("atlasDbms"));
		return sqlString;
	}
}
