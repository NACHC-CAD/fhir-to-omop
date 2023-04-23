package org.nachc.tools.fhirtoomop.tools.databricks.createschemafromcdmddl;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
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
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		String schemaName = DatabricksProperties.getSchemaName();
		// check the connection
		conn = checkConnection(conn);
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
	
	private static Connection checkConnection(Connection conn) {
		try {
			Database.query("select 1", conn);
			log.info("Connection is good.");
		} catch(Exception exp) {
			log.info("Connection was bad, creating a new one.");
			Database.close(conn);
			log.info("Old connection closed.");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("New connection created.");
		}
		return conn;
	}

}
