package org.nachc.tools.fhirtoomop.tools.populate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateDataTables;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * THIS CLASS WILL TRUNCATE ALL OF THE DATATABLES BEFORE RUNNING.  
 * 
 * This class populates the OMOP instance defined in the app.properties file
 * with files from the directory specified in the app.properties dir by default.
 * 
 * The dir name can also be handed in as a parameter.
 *
 */

@Slf4j
public class PopulateFromFiles {


	//
	// number of connections to the database used by the threads
	//
	
	private static final int MAX_CONNS = 10;

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		exec(null);
	}

	public static void exec(String dirName) {
		// get a connection (used to truncate the data tables
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			// get the list of files
			log.info("Getting files...");
			List<String> fileList;
			if (dirName == null) {
				fileList = AppParams.getFhirPatientsDirListing();
			} else {
				fileList = FileUtil.listResources(dirName, PopulateFromFiles.class);
			}
			// truncate the datatables
			List<String> tableNames = DatatableList.getDatatableList();
			log.info("Truncating tables...");
			TruncateDataTables.truncateTables(tableNames, conn);
			Database.commit(conn);
			// write the files
			log.info("Writing all files...");
			List<Connection> connList = getConnections();
			PopulateFromFileList.writeAllFiles(fileList, connList);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

	private static List<Connection> getConnections() {
		ArrayList<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < MAX_CONNS; i++) {
			Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
			rtn.add(conn);
		}
		return rtn;
	}

}
