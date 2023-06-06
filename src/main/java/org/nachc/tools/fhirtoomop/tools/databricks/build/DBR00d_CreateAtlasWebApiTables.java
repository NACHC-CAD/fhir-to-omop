package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBR00d_CreateAtlasWebApiTables {

	private static final String FILE_PATH = "/databricks/webapi/A10_CreateAtlasWebApiTables.sql";

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
		log.info("Creating Achilles tables.");
		log.info("getting sql script...");
		log.info("executing script...");
		String sqlString = getSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating Achilles tables.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("@webApiSchema", DatabricksProperties.getWebApiSchema());
		sqlString = sqlString.replace("@ohdsiAdminUser", DatabricksProperties.get("OhdsiAdminUserUid"));
		return sqlString;
	}

}
