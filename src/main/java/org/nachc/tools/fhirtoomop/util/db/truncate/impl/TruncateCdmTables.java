package org.nachc.tools.fhirtoomop.util.db.truncate.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.db.truncate.TruncateTables;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateCdmTables extends TruncateTables {

	//
	// main method (for manual testing)
	//

	public static void main(String[] args) {
		// truncateAllTables();
		// truncateDataTables();
		// truncateVocabularyTables();
	}

	// ---
	//
	// PUBLIC STATIC METHODS
	//
	// ---
	
	//
	// truncate all tables
	//
	
	public static void truncateAllTables() {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		TruncateCdmTables trunk = new TruncateCdmTables();
		trunk.truncateAllTables(dbName, schemaName, conn);
		log.info("Done.");
	}

	//
	// truncate only the data tables (keep terminology)
	//

	public static void truncateDataTables() {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		TruncateCdmTables trunk = new TruncateCdmTables();
		trunk.truncateDataTables(dbName, schemaName, conn);
		log.info("Done.");
	}

	//
	// truncate only the terminology tables (keep data)
	//

	public static void truncateVocabularyTables() {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		TruncateCdmTables trunk = new TruncateCdmTables();
		trunk.setInvertIgnore(true);
		trunk.truncateDataTables(dbName, schemaName, conn);
		log.info("Done.");
	}

	// ---
	//
	// PUBLIC INSTANCE METHODS TO PERFORM TRUNCATE BASED ON PASSED IN PARAMETERS
	//
	// ---
	
	//
	// truncate all tables
	//
	
	public void truncateAllTables(String dbName, String schemaName, Connection conn) {
		this.setIgnoreList(new ArrayList<String>());
		List<String> allTables = getTablesForSchema(dbName, schemaName, conn);
		for (String tableName : allTables) {
			truncateTable(dbName, schemaName, tableName, conn);
		}
		logOutcomes();
	}

	//
	// truncate only the data tables
	//

	public void truncateDataTables(String dbName, String schemaName, Connection conn) {
		this.setIgnoreList(VocabularyTablesList.getTablesList());
		List<String> allTables = getTablesForSchema(dbName, schemaName, conn);
		for (String tableName : allTables) {
			truncateTable(dbName, schemaName, tableName, conn);
		}
		logOutcomes();
	}

	//
	// truncate only the vocabulary tables
	//

	public void truncateVocabularyTables(String dbName, String schemaName, Connection conn) {
		this.setInvertIgnore(true);
		this.truncateDataTables(dbName, schemaName, conn);
	}

}
