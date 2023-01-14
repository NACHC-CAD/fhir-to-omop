package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.threaded.WriteOmopPeopleToDatabase;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
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

	private List<Connection> connectionList = null;
	
	public PopulateOmopInstanceFromFhirFiles() {
		log.info("No database connections provided");
	}
	
	public PopulateOmopInstanceFromFhirFiles(List<Connection> connectionList) {
		log.info("Using provided database connections");
		this.connectionList = connectionList;
	}
	
	public static void main(String[] args) {
		new PopulateOmopInstanceFromFhirFiles().exec();
	}

	public void exec() {
		String rootDir = AppParams.getFhirPatientsDirName();
		log.info("Root Dir: " + rootDir);
		List<String> fileList = getFiles();
		exec(fileList);
	}
	
	private List<String> getFiles() {
		File dir = new File(AppParams.getFhirPatientsDirName());
		File[] files = dir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		int cnt = 0;
		try {
			for(File file : files) {
				cnt++;
				fileNames.add(file.getCanonicalPath());
				if(cnt % 10000 == 0) {
					log.info(cnt + "");
				}
			}
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
		return fileNames;
	}
	
	public void exec(List<String> fileList) {
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
		int numberOfPatientsBefore = -1;
		int numberOfPatientsAfter = -1;
		WriteOmopPeopleToDatabase writer;
		try {
			// populate the caches
			log.info("CREATING MAPPED CONCEPT CACHCE...");
			MappedConceptCache.init(connList.get(0));
			log.info("CREATING STANDARD CONCEPT CACHCE...");
			StandardConceptCache.init(connList.get(0));
			// truncate existing data
//			log.info("TRUNCATING DATA TABLES");
//			TruncateAllDataTables.exec();
			// do the priming step
			log.info("Doing priming step...");
			new PrimeUploadProcess(this.connectionList).exec();
			// truncate data uploaded by priming step
//			log.info("TRUNCATING DATA TABLES");
//			TruncateAllDataTables.exec(connectionList.get(0));
			// do the upload
			numberOfPatientsBefore = Database.count("person", connList.get(0));
			log.info("Creating writer");
			timer.start();
			writer = new WriteOmopPeopleToDatabase(fileList, connList, numberOfWorkers, numberOfPatientsPerWorker, numberOfThreadsPerWorker);
			writer.exec();
			numberOfPatientsAfter = Database.count("person", connList.get(0));
			log.info("Doing updates for " + numberOfPatientsAfter + " patients");
			updatePrevVisit();
		} finally {
			closeConnections(connList);
			timer.stop();
			log.info("***************************************************************");
			log.info("! ! ! DONE WRITING PATIENTS TO DATABASE ! ! !");
			log.info("------------");
			log.info("START:   " + timer.getStartAsString());
			log.info("STOP:    " + timer.getStopAsString());
			log.info("ELAPSED: " + timer.getElapsedString());
			log.info("PATIENT COUNTS");
			log.info("BEFORE:  " + numberOfPatientsBefore);
			log.info("AFTER:   " + numberOfPatientsAfter);
			log.info("------------");
			log.info("! ! ! DONE WRITING PATIENTS TO DATABASE ! ! !");
			log.info("***************************************************************");
			log.info("");
			log.info("Your OMOP database now has " + numberOfPatientsAfter + " patients.");
		}
		log.info("Done populating from files.");
	}

	private List<Connection> getConnections(int maxConns) {
		if(this.connectionList == null) {
			this.connectionList = new ArrayList<Connection>();
			for (int i = 0; i < maxConns; i++) {
				this.connectionList.add(OmopDatabaseConnectionFactory.getOmopConnection());
			}
		}
		return this.connectionList;
	}

	private void closeConnections(List<Connection> connList) {
		for (Connection conn : connList) {
			OmopDatabaseConnectionFactory.close(conn);
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
		Connection conn = null;
		if("postgres".equals(AppParams.get("cdmDbType"))) {
			conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		} else {
			conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		}
		try {
			if("mssql".equals(AppParams.get("cdmDbType"))) {
				String dbName = AppParams.getDbName();
				Database.update("use " + dbName, conn);
			}
			Database.executeSqlScript(is, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done updating prev visit.");
	}

}
