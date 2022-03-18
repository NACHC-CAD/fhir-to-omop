package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateDataTables;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFromFiles {

	private static final int MAX_THREADS = 2000;
	
	private static final int MAX_CONNS = 10;

	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Getting files...");
			List<String> fileList = AppParams.getFhirPatientsDirListing();
			int cnt = 0;
			List<String> tableNames = DatatableList.getDatatableList();
			log.info("Truncating tables...");
			TruncateDataTables.truncateTables(tableNames, conn);
			Database.commit(conn);
			log.info("Writing all files...");
			List<Connection> connList = getConnections();
			writeAllFiles(fileList, connList);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

	private static void writeAllFiles(List<String> fileList, List<Connection> connList) {
		ArrayList<String> filesToWrite = new ArrayList<String>();
		int cnt = 0;
		for (int i = 0; i < fileList.size(); i++) {
			filesToWrite.add(fileList.get(i));
			if (i % MAX_THREADS == 0 && i != 0) {
				new WriteAllFilesToOmop().exec(filesToWrite, connList);
				for (Connection conn : connList) {
					Database.commit(conn);
				}
				cnt++;
				filesToWrite = new ArrayList<String>();
				logMsg(i, cnt);
			}
		}
		if (filesToWrite.size() > 0) {
			new WriteAllFilesToOmop().exec(filesToWrite, connList);
		}
	}

	private static List<Connection> getConnections() {
		ArrayList<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < MAX_CONNS; i++) {
			Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
			rtn.add(conn);
		}
		return rtn;
	}

	private static void logMsg(int i, int cnt) {
		String msg = "";
		msg += "\n\n\n\n------------------------------------------------";
		msg += "\nWriting Datafiles";
		msg += "\nTotal written: " + i;
		msg += "\nBlock count:   " + cnt;
		msg += "\n------------------------------------------------\n\n\n";
		log.info(msg);
	}

}
