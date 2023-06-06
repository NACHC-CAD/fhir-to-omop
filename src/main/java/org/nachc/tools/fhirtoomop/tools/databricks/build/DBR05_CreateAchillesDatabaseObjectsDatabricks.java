package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.database.DatabricksDatabase;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/*
 * 
 * CDM ???
 * I would think this script would be version dependent but I'm not seeing where this is configured?
 * 
 * This class runs the script that is created by running:
 * http://localhost:8080/WebAPI/ddl/results?dialect=spark&schema=<ACHILLES_RESULTS_SCHEMA_NAME>&vocabSchema=<VOCAB_SCHEMA_NAME>&tempSchema=<ACHILLES_TEMP_SCHEMA_NAME>&initConceptHierarchy=true
 * This creates the Achilles results and Achilles temp database objects (tables). 
 *
 */

@Slf4j
public class DBR05_CreateAchillesDatabaseObjectsDatabricks {


	private static final String DDL_FILE = "/databricks/achilles/create-achilles-tables-ddl.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String vocabSchemaName = DatabricksProperties.getVocabSchemaName();
			String achillesTempDatabaseName = DatabricksProperties.getAchillesTempSchemaName();
			String achillesResultsDatabaseName = DatabricksProperties.getAchillesResultsSchemaName();
			exec(vocabSchemaName, achillesTempDatabaseName, achillesResultsDatabaseName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String vocabSchemaName, String achillesTempSchemaName, String achillesResultsSchemaName, Connection conn) {
		// check the connection 
		conn = DatabricksDatabase.resetConnectionIfItIsBad(conn);
		// echo status
		log.info("-------------------------------");
		log.info("START: Creating Achilles Databases (vocab, temp, and results): " + vocabSchemaName + ", " + achillesTempSchemaName +  ", " + achillesResultsSchemaName);
		log.info("-------------------------------");
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(DDL_FILE);
		String sqlString = FileUtil.getAsString(is);
		// update the parameters
		sqlString = replace(sqlString, "<VOCAB_SCHEMA_NAME>", vocabSchemaName);
		sqlString = replace(sqlString, "<ACHILLES_TEMP_SCHEMA_NAME>", achillesTempSchemaName);
		sqlString = replace(sqlString, "<ACHILLES_RESULTS_SCHEMA_NAME>", achillesResultsSchemaName);
		// exceptions occur if the following is not set
		log.info("SETTING: set spark.sql.ansi.enabled=false");
		Database.query("set spark.sql.ansi.enabled=false", conn);
		// create the tables
		log.info("Creating database objects...");
		Database.executeSqlScript(sqlString, conn);
		// echo status
		log.info("-------------------------------");
		log.info("DONE: Creating Achilles Databases (vocab, temp, and results): " + vocabSchemaName + ", " + achillesTempSchemaName, ", " + achillesResultsSchemaName);
		log.info("-------------------------------");
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}
	
}
