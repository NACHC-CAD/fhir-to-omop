package org.nachc.tools.fhirtoomop.tools.populate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateDataTables;
import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFromFiles {

	private static final int MAX_CONNS = 10;

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		exec(null);
	}
	
	public static void exec(String dirName) {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			log.info("Getting files...");
			List<String> fileList;
			if(dirName == null) {
				fileList = AppParams.getFhirPatientsDirListing();
			} else {
				fileList = FileUtil.listResources(dirName, PopulateFromFiles.class);
			}
			int cnt = 0;
			List<String> tableNames = DatatableList.getDatatableList();
			log.info("Truncating tables...");
			TruncateDataTables.truncateTables(tableNames, conn);
			Database.commit(conn);
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
