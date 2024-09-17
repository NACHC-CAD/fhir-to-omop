package org.nachc.tools.fhirtoomop.util.db.truncate.impl;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.truncate.TruncateTables;
import org.nachc.tools.fhirtoomop.util.postgres.exporttables.CdmTablesForPostgres;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateTablesForPostgres extends TruncateTables {

	//
	// main method (for manual testing)
	//

	public static void main(String[] args) {
		// truncateAllTables();
		// truncateDataTables();
		// truncateVocabularyTables();
	}

	//
	// method to get all of the tables for a schema
	//
	
	public List<String> getTablesForSchema(String dbName, String schemaName, Connection conn) {
		List<String> allTables = CdmTablesForPostgres.getAllCdmTables(conn);
		return allTables;
	}

}
