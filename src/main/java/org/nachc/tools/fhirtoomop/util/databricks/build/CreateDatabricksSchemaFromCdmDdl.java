package org.nachc.tools.fhirtoomop.util.databricks.build;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabricksSchemaFromCdmDdl {

	private static final String DDL_FILE = "/databricks/cdm/spark/OMOPCDM_spark_5.4_ddl.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksConnectionFactory.getConnection();
			String schemaName = "demo_cdm";
			exec(schemaName, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String schemaName, Connection conn) {
		// get the sql from the ddl file
		log.info("Getting ddl file...");
		InputStream is = FileUtil.getInputStream(DDL_FILE);
		String sqlString = FileUtil.getAsString(is);
		sqlString = replace(sqlString, "@cdmDatabaseSchema", schemaName);
		// drop the existing schema
		log.info("Dropping existing database...");
		Database.update("drop database if exists " + schemaName + " cascade", conn);
		// create the database
		log.info("Creating the database...");
		Database.update("create database " + schemaName, conn);
		// populate the database from the ddl sql
		log.info("Creating database objects...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating database.");
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}

}