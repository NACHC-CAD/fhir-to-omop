package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateWebApiRecords {

	public static final String SOURCE_FILE_NAME = "/sqlserver/omop/webapi-source.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		log.info("Getting connection...");
		Connection conn = PostgresDatabaseConnectionFactory.getWebApiConnection();
		try {
			String sqlString = FileUtil.getAsString(SOURCE_FILE_NAME);
			sqlString = replace(sqlString, "@WebApiConnectionString", AppParams.get("WebApiConnectionString"));
			sqlString = replace(sqlString, "@WebApiSourceName", AppParams.get("WebApiSourceName"));
			sqlString = replace(sqlString, "@WebApiSourceKey", AppParams.get("WebApiSourceKey"));
			sqlString = replace(sqlString, "@WebApiSourceJdbcUrl", AppParams.get("WebApiSourceJdbcUrl"));
			sqlString = replace(sqlString, "@WebApiSourceDialect", AppParams.get("WebApiSourceDialect"));
			log.info("SQL STRING FOR SOURCE UPDATE: \n" + sqlString);
			log.info("Update completed.");
		} finally {
			Database.close(conn);
			log.info("Database closes");
		}
		log.info("Done with source record update.");
	}
	
	private static String replace(String str, String src, String dst) {
		str = str.replaceAll(src, dst);
		return str;
	}
}
