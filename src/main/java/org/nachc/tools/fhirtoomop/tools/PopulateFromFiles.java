package org.nachc.tools.fhirtoomop.tools;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateDataTables;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFromFiles {

	private static final int MAX_THREADS = 1000;

	public static void main(String[] args) {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			log.info("Getting files...");
			File dir = AppParams.getFullSetOfSyntheaPatientsDir();
			int cnt = 0;
			List<String> tableNames = DatatableList.getDatatableList();
			log.info("Truncating tables...");
			TruncateDataTables.truncateTables(tableNames, conn);
			log.info("Writing all files...");
			writeAllFiles(dir, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	private static void writeAllFiles(File dir, Connection conn) {
		File[] fileList = dir.listFiles();
		ArrayList<File> filesToWrite = new ArrayList<File>();
		int cnt = 0;
		for (int i = 0; i < fileList.length; i++) {
			filesToWrite.add(fileList[i]);
			if (i % MAX_THREADS == 0 && i != 0) {
				new WriteAllFilesToOmop().exec(filesToWrite, conn);
				Database.commit(conn);
				cnt++;
				filesToWrite = new ArrayList<File>();
				logMsg(i, cnt);
			}
		}
		if(filesToWrite.size() > 0) {
			new WriteAllFilesToOmop().exec(filesToWrite, conn);
		}
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
