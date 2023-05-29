package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A07_RunAchillesScript {

	public static final String ACHILLES_SCRIPT = "/databricks/achilles/achilles.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String schemaName = DatabricksProperties.getSchemaName();
			String achillesResultsDatabaseName = DatabricksProperties.getAchillesResultsDatabaseName();
			exec(schemaName, achillesResultsDatabaseName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, String achillesResultsSchemaName, Connection conn) {
		// check the connection
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(ACHILLES_SCRIPT);
		String sqlString = FileUtil.getAsString(is);
		sqlString = replace(sqlString, "<CDM_DATABASE_NAME>", schemaName);
		sqlString = replace(sqlString, "<ACHILLES_RESULTS_DATABASE_NAME>", achillesResultsSchemaName);
		// populate the database from the script
		log.info("Running Achilles Script...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done Running Achilles Script.");
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}

}
