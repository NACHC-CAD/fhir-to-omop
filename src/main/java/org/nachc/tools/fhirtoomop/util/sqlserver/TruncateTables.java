package org.nachc.tools.fhirtoomop.util.sqlserver;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateTables {

	//
	// instance variables
	//

	private ArrayList<String> success = new ArrayList<String>();

	private ArrayList<String> failure = new ArrayList<String>();

	private ArrayList<String> ignored = new ArrayList<String>();

	private List<String> ignoreList = VocabularyTablesList.getTablesList();
	
	private boolean invertIgnore = false;

	private List<String> allTables = null;

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
		TruncateTables trunk = new TruncateTables();
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
		TruncateTables trunk = new TruncateTables();
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
		TruncateTables trunk = new TruncateTables();
		trunk.invertIgnore = true;
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
		this.ignoreList = new ArrayList<String>();
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
		this.ignoreList = VocabularyTablesList.getTablesList();
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
		this.invertIgnore = true;
		this.truncateDataTables(dbName, schemaName, conn);
	}

	// internal implementation (all private past here) ------------------------
	
	//
	// method to get all of the tables for a schema
	//
	
	private List<String> getTablesForSchema(String dbName, String schemaName, Connection conn) {
		if (allTables == null) {
			allTables = new ArrayList<String>();
			String sqlString = "";
			sqlString += "select table_name from information_schema.tables \n";
			sqlString += "where table_catalog = '" + dbName + "' \n";
			sqlString += "and table_schema = '" + schemaName + "' \n";
			Database.update("use " + dbName, conn);
			Data data = Database.query(sqlString, conn);
			for (Row row : data) {
				String str = row.get("tableName");
				allTables.add(str);
			}
		}
		return allTables;
	}

	//
	// methods to see if a table should be ignored
	//
	
	private boolean ignore(String tableName) {
		boolean isOnIgnoreList = isOnIgnoreList(tableName);
		if(invertIgnore == true) {
			return !isOnIgnoreList;
		} else {
			return isOnIgnoreList;
		}
	}

	private boolean isOnIgnoreList(String tableName) {
		if (ignoreList.contains(tableName.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	//
	// truncate table method
	//
	
	private void truncateTable(String dbName, String schemaName, String tableName, Connection conn) {
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
	
	private void logOutcomes() {
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

}
