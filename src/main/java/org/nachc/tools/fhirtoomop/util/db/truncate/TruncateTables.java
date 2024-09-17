package org.nachc.tools.fhirtoomop.util.db.truncate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.db.truncate.impl.TruncateTablesForSqlServer;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public abstract class TruncateTables {

	//
	// instance variables
	//

	protected ArrayList<String> success = new ArrayList<String>();

	protected ArrayList<String> failure = new ArrayList<String>();

	protected ArrayList<String> ignored = new ArrayList<String>();

	protected List<String> ignoreList = VocabularyTablesList.getTablesList();
	
	protected boolean invertIgnore = false;

	protected List<String> allTables = null;

	//
	// abstract methods
	//
	
	public abstract List<String> getTablesForSchema(String dbName, String schemaName, Connection conn);
	
	//
	// methods to see if a table should be ignored
	//
	
	public boolean ignore(String tableName) {
		boolean isOnIgnoreList = isOnIgnoreList(tableName);
		if(invertIgnore == true) {
			return !isOnIgnoreList;
		} else {
			return isOnIgnoreList;
		}
	}

	public boolean isOnIgnoreList(String tableName) {
		if (ignoreList.contains(tableName.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	//
	// truncate table method
	//
	
	public void truncateTable(String dbName, String schemaName, String tableName, Connection conn) {
		String fullTableName = dbName + "." + schemaName + "." + tableName;
		log.info("Truncating table: " + fullTableName);
		if (ignore(tableName) == true) {
			ignored.add(fullTableName);
			log.info("Ignored table: " + fullTableName);
		} else {
			try {
				try {
					Database.update("truncate table " + fullTableName, conn);
				} catch (Exception exp) {
					log.info("TRUNCATE METHOD FAILD, USING DELETE: " + fullTableName);
					Database.update("delete from " + fullTableName, conn);
				}
				success.add(fullTableName);
				log.info("Successfully truncated table: " + fullTableName);
			} catch (Exception exp) {
				log.info("An error occured trying to truncate table: " + tableName);
				failure.add(schemaName + "." + fullTableName);
			}
		}
	}

	//
	// log the outcome
	//
	
	public void logOutcomes() {
		String msg;
		msg = "\n--------------------\n";
		msg += "Success: The following tables were successfully truncated. \n";
		for (String str : success) {
			msg += "  " + str + "\n";
		}
		msg += "--------------------\n";
		log.info(msg);
		if (ignored.size() > 0) {
			msg = "\n--------------------\n";
			msg += "The following files were ignored: \n";
			for (String str : ignored) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "No files were ignored.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
		if (failure.size() > 0) {
			msg = "\n--------------------\n";
			msg += "! ! ! SOME TABLES FAILED ! ! !\n";
			for (String str : failure) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "There were no errors.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
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

	// * * *
	//
	// IMPLEMENTATION
	//
	// * * *
	
	//
	// truncate all tables
	//
	
	public void truncateAllTables() {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		this.truncateAllTables(dbName, schemaName, conn);
		log.info("Done.");
	}

	//
	// truncate only the data tables (keep terminology)
	//

	public void truncateDataTables(Connection conn) {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		this.truncateDataTables(dbName, schemaName, conn);
		log.info("Done.");
	}

	//
	// truncate only the terminology tables (keep data)
	//

	public void truncateVocabularyTables() {
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		try {
			truncateVocabularyTables(conn);
		} finally {
			Database.close(conn);
		}
	}
	
	public void truncateVocabularyTables(Connection conn) {
		String dbName = AppParams.getDatabaseName();
		String schemaName = AppParams.getSchemaName();
		this.setInvertIgnore(true);
		this.truncateDataTables(dbName, schemaName, conn);
		log.info("Done.");
	}

}
