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
public class A05_CreateAchillesDatabaseObjectsDatabricks {

	private static final String DDL_FILE = "/databricks/achilles/create-achilles-tables-ddl.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String schemaName = DatabricksProperties.getSchemaName();
			exec(schemaName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, Connection conn) {
		// check the connection
		conn = checkConnection(conn);
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(DDL_FILE);
		String sqlString = FileUtil.getAsString(is);
		sqlString = replace(sqlString, "<DB_NAME>", schemaName);
		// populate the database from the ddl sql
		log.info("Creating database objects...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating database.");
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}
	
	private static Connection checkConnection(Connection conn) {
		return DatabricksDatabase.resetConnectionIfItIsBad(conn);
	}

}
