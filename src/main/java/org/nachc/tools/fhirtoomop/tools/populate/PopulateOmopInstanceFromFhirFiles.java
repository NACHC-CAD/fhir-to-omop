package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.listofpatients.WriteListOfFhirPatientsToOmop;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateOmopInstanceFromFhirFiles {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		log.info("TRUNCATING DATA TABLES");
		TruncateAllDataTables.exec();
		log.info("Populating OMOP instance from files...");
		String rootDir = AppParams.getFhirPatientsDirName();
		int maxConns = AppParams.getMaxNumberOfConnectionsForUpload();
		int maxThreads = AppParams.getMaxNumberOfThreadsForUpload();
		log.info("Root Dir: " + rootDir);
		log.info("maxConns: " + maxConns);
		log.info("maxThreads: " + maxThreads);
		List<String> fileList = FileUtil.listResources(rootDir, PopulateOmopInstanceFromFhirFiles.class);
		List<Connection> connList = getConnections(maxConns);
		log.info("Got " + fileList.size() + " patients.");
		log.info("Got " + connList.size() + " connections.");
		Timer timer =  new Timer();
		timer.start();
		int cnt = 0;
		try {
			WriteListOfFhirPatientsToOmop.exec(fileList, connList, maxThreads);
			cnt = Database.count("person", connList.get(0));
			log.info("Doing updates for " + cnt + " patients");
			updatePrevVisit();
		} finally {
			closeConnections(connList);
			timer.stop();
			log.info("------------");
			log.info(timer.getStartAsString());
			log.info(timer.getStopAsString());
			log.info(timer.getElapsedString());
			log.info("------------");
			log.info("");
			log.info("Your OMOP database now has " + cnt + " patients.");
		}
		log.info("Done.");
	}

	private static List<Connection> getConnections(int maxConns) {
		List<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < maxConns; i++) {
			rtn.add(OmopDatabaseConnectionFactory.getOmopConnection());
		}
		return rtn;
	}

	private static void closeConnections(List<Connection> connList) {
		for (Connection conn : connList) {
			Database.close(conn);
		}
	}
	
	private static void updatePrevVisit() {
		log.info("Updating prev visit records...");
		String filePath = "/sqlserver/omop/update-prev-visit.sql";
		InputStream is = FileUtil.getInputStream(filePath);
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			String dbName = AppParams.getDbName();
			Database.update("use " + dbName, conn);
			Database.executeSqlScript(is, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done updating prev visit.");
	}

}
