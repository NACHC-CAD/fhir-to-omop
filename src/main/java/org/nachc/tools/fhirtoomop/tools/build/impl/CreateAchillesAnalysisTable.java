package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAchillesAnalysisTable {

	private static final String DDL_FILE = "/sqlserver/omop/achilles/sql/create-achilles-analysis-table.sql";
	
	private static final String UPLOAD_FILE = "/sqlserver/omop/achilles/achilles_analysis_details.csv";
	
	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}
	
	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		exec(conn);
	}
	
	public static void exec(Connection conn) {
		createTable(conn);
		uploadData(conn);
	}
	
	private static void createTable(Connection conn) {
		String sqlString = FileUtil.getAsString(DDL_FILE);
		String cdmSchema = AppParams.getFullySpecifiedSchemaName();
		String resSchema = AppParams.getFullySpecifiedAchilliesResultsSchemaName();
		sqlString = sqlString.replaceAll("@FullySpecifiedCdmSchema", cdmSchema);
		sqlString = sqlString.replaceAll("@FullySpecifiedAchillesResultsSchema", resSchema);
		log.info("Running Achilles SQL script...");
		Database.executeSqlScript(sqlString, conn);
		log.info("Done executing Achilles sql script.");
	}
	
	private static void uploadData(Connection conn) {
		log.info("Uploading data...");
		File dir = new File("./delete_me_achilles");
		try {
			FileUtil.mkdirs(dir);
			File tempFile = new File(dir, "temp.txt");
			String data = FileUtil.getAsString(UPLOAD_FILE);
			FileUtil.write(data, tempFile);
			String resSchema = AppParams.getFullySpecifiedAchilliesResultsSchemaName();
			String tableName = resSchema + ".achilles_analysis";
			String sqlString = "";
			sqlString += "BULK INSERT " + tableName + " \n";
			sqlString += "FROM '" + FileUtil.getCanonicalPath(tempFile) + "' \n";
			sqlString += "WITH \n";
			sqlString += "( \n";
			sqlString += "    FIELDTERMINATOR = ',', \n";
			sqlString += "    ROWTERMINATOR = '\n', \n";
			sqlString += "    FIRSTROW = 2, \n";
			sqlString += "    FORMAT = 'CSV', \n";
			sqlString += "    TABLOCK \n";
			sqlString += ") \n";		Database.update("truncate table " + tableName, conn);
			log.info("Populating table...");
			Database.update(sqlString, conn);
			log.info("Done creating achilles_analysis table.");
		} finally {
			FileUtil.rmdir(dir);
		}
	}
	
}
