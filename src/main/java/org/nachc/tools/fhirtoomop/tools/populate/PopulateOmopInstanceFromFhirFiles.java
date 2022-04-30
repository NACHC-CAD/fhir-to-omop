package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.threaded.WriteOmopPeopleToDatabase;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.MappedConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.StandardConceptCache;
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
		String rootDir = AppParams.getFhirPatientsDirName();
		log.info("Root Dir: " + rootDir);
		List<String> fileList = FileUtil.listResources(rootDir, PopulateOmopInstanceFromFhirFiles.class);
		exec(fileList);
	}
	
	public void exec(List<String> fileList) {
		log.info("TRUNCATING DATA TABLES");
		TruncateAllDataTables.exec();
		log.info("Populating OMOP instance from files...");
		int maxConns = AppParams.getMaxNumberOfConnectionsForUpload();
		int numberOfThreadsPerWorker = AppParams.getMaxNumberOfThreadsForUpload();
		int numberOfPatientsPerWorker = numberOfThreadsPerWorker;
		int numberOfWorkers = AppParams.getMaxNumberOfWorkersForUpload();
		log.info("maxConns: " + maxConns);
		log.info("maxThreads: " + numberOfThreadsPerWorker);
		log.info("maxWorkers: " + numberOfWorkers);
		log.info("Getting all files (this can take a couple of minutes).");
		log.info("(last updated 2022-04-23 -- Using Executors)");
		List<Connection> connList = getConnections(maxConns);
		log.info("Got " + fileList.size() + " patients.");
		log.info("Got " + connList.size() + " connections.");
		Timer timer = new Timer();
		timer.start();
		int numberOfPatients = 0;
		WriteOmopPeopleToDatabase writer;
		try {
			log.info("CREATING MAPPED CONCEPT CACHCE...");
			MappedConceptCache.init(connList.get(0));
			log.info("CREATING STANDARD CONCEPT CACHCE...");
			StandardConceptCache.init(connList.get(0));
			log.info("Creating writer");
			writer = new WriteOmopPeopleToDatabase(fileList, connList, numberOfWorkers, numberOfPatientsPerWorker, numberOfThreadsPerWorker);
			writer.exec();
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
		log.info("Done populating from files.");
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
