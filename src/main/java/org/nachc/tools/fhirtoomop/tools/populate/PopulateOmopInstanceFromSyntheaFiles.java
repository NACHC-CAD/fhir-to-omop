package org.nachc.tools.fhirtoomop.tools.populate;

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
public class PopulateOmopInstanceFromSyntheaFiles {

	public static void main(String[] args) {
		new PopulateOmopInstanceFromSyntheaFiles().exec();
	}

	public void exec() {
		List<String> dirList = getDirList();
		exec(dirList);
	}

	private List<String> getDirList() {
		String rootDirName = AppParams.getSyntheaPatientsDirName();
		log.info("Root Dir: " + rootDirName);
		List<String> fileNames = FileUtil.listResources(rootDirName, getClass());
		return fileNames;
	}

	public void exec(List<String> dirList) {
		log.info("Populating OMOP instance from files...");
		int maxConns = AppParams.getMaxNumberOfConnectionsForUpload();
		int numberOfThreadsPerWorker = AppParams.getMaxNumberOfThreadsForUpload();
		int numberOfPatientsPerWorker = numberOfThreadsPerWorker;
		int numberOfWorkers = AppParams.getMaxNumberOfWorkersForUpload();
		log.info("maxConns: " + maxConns);
		log.info("maxThreads: " + numberOfThreadsPerWorker);
		log.info("maxWorkers: " + numberOfWorkers);
		log.info("Getting all files (this can take a couple of minutes).");
		List<Connection> connList = getConnections(maxConns);
		log.info("Got " + dirList.size() + " directories.");
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
			log.info("TRUNCATING DATA TABLES");
			TruncateAllDataTables.exec();
			// do the priming step
			log.info("Doing priming step...");
			new PrimeUploadProcess().exec();
			// truncate data uploaded by priming step
			log.info("TRUNCATING DATA TABLES");
			TruncateAllDataTables.exec();
			// do the upload
			numberOfPatientsBefore = Database.count("person", connList.get(0));
			log.info("Creating writer");
			timer.start();
			for (String str : dirList) {
				List<String> subDirList = FileUtil.listResources(str, getClass());
				for (String subDirName : subDirList) {
					if (subDirName.endsWith("fhir")) {
						log.info("WRITING DATA FOR: " + subDirName);
						List<String> fileList = FileUtil.listResources(subDirName, getClass());
						writer = new WriteOmopPeopleToDatabase(fileList, connList, numberOfWorkers, numberOfPatientsPerWorker, numberOfThreadsPerWorker);
						writer.exec();
					}
				}
			}
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
		List<Connection> rtn = new ArrayList<Connection>();
		for (int i = 0; i < maxConns; i++) {
			rtn.add(OmopDatabaseConnectionFactory.getOmopConnection());
		}
		return rtn;
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
