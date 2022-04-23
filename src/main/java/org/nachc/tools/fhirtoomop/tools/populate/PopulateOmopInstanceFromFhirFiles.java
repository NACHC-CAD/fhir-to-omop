package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.threaded.WriteOmopPeopleToDatabase;
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
		new PopulateOmopInstanceFromFhirFiles().exec();
	}

	public void exec() {
		log.info("TRUNCATING DATA TABLES");
		TruncateAllDataTables.exec();
		log.info("Populating OMOP instance from files...");
		String rootDir = AppParams.getFhirPatientsDirName();
		int maxConns = AppParams.getMaxNumberOfConnectionsForUpload();
		int maxThreads = AppParams.getMaxNumberOfThreadsForUpload();
		int maxWorkers = AppParams.getMaxNumberOfWorkersForUpload();
		log.info("Root Dir: " + rootDir);
		log.info("maxConns: " + maxConns);
		log.info("maxThreads: " + maxThreads);
		log.info("maxWorkers: " + maxWorkers);
		log.info("Getting all files (this can take a couple of minutes).");
		log.info("(last updated 2022-04-23 -- Using Executors)");
		List<String> fileList = FileUtil.listResources(rootDir, PopulateOmopInstanceFromFhirFiles.class);
		List<Connection> connList = getConnections(maxConns);
		log.info("Got " + fileList.size() + " patients.");
		log.info("Got " + connList.size() + " connections.");
		Timer timer = new Timer();
		timer.start();
		int numberOfPatients = 0;
		int cnt = 0;
		int patientsPerRun = 10000;
		WriteOmopPeopleToDatabase writer;
		try {
			List<String> filesForJob = new ArrayList<String>();
			while (fileList.size() > 0) {
				filesForJob.add(fileList.remove(0));
				cnt++;
				if(cnt % patientsPerRun == 0) {
					writer = new WriteOmopPeopleToDatabase(filesForJob, connList, maxWorkers, maxThreads);
					writer.exec();
				}
			}
			if(filesForJob != null && filesForJob.size() > 0) {
				writer = new WriteOmopPeopleToDatabase(filesForJob, connList, maxWorkers, maxThreads);
				writer.exec();
			}
			numberOfPatients = Database.count("person", connList.get(0));
			log.info("Doing updates for " + numberOfPatients + " patients");
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
			log.info("Your OMOP database now has " + numberOfPatients + " patients.");
		}
		log.info("Done.");
	}

	private List<Connection> getConnections(int maxConns) {
		List<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < maxConns; i++) {
			rtn.add(OmopDatabaseConnectionFactory.getOmopConnection());
		}
		return rtn;
	}

	private void closeConnections(List<Connection> connList) {
		for (Connection conn : connList) {
			Database.close(conn);
		}
	}

	private void updatePrevVisit() {
		log.info("* * *");
		log.info("* * *");
		log.info("* * *");
		log.info("UPDATING PREVIOUS VISIT RECORDS");
		log.info("* * *");
		log.info("* * *");
		log.info("* * *");
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
