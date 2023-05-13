package org.nachc.tools.fhirtoomop.tools.databricks.primarykeys;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreatePrimaryKeysFromCdmDdl {

	private static final String DDL_FILE = "/databricks/cdm/spark/OMOPCDM_spark_5.4_primary_keys.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String schemaName = DatabricksProperties.getSchemaName();
		log.info("SCHEMA_NAME: " + schemaName);
		// check the connection
		conn = checkConnection(conn);
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(DDL_FILE);
		String sqlString = FileUtil.getAsString(is);
		sqlString = replace(sqlString, "@cdmDatabaseSchema", schemaName);
		// execute the script
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
