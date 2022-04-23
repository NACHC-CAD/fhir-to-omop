package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file.FhirPatientResourcesAsFilesFactory;
import org.nachc.tools.fhirtoomop.omop.write.threaded.executor.ExecutorManager;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseWorkerRunnable;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabase {

	private final Object LOCK = new Object();

	private List<String> fileList;

	private List<Connection> connList;

	private int numberOfWorkers;

	private int numberOfPatientsPerWorker;

	private List<WriteOmopPeopleToDatabaseWorker> active = new ArrayList<WriteOmopPeopleToDatabaseWorker>();

	public WriteOmopPeopleToDatabase(List<String> fileList, List<Connection> connList, int numberOfWorkers, int numberOfPatientsPerWorker) {
		this.fileList = fileList;
		this.connList = connList;
		this.numberOfWorkers = numberOfWorkers;
		this.numberOfPatientsPerWorker = numberOfPatientsPerWorker;
	}

	private WriteOmopPeopleToDatabaseWorker getNextWorker() {
		if (fileList.size() == 0) {
			return null;
		}
		List<String> filesForNextWorker = new ArrayList<String>();
		for (int i = 0; i < numberOfPatientsPerWorker; i++) {
			if (fileList.size() > 0) {
				filesForNextWorker.add(fileList.remove(0));
			} else {
				break;
			}
		}
		List<FhirPatientResources> resourcesForNextWorker = FhirPatientResourcesAsFilesFactory.getForList(filesForNextWorker);
		WriteOmopPeopleToDatabaseWorker worker = new WriteOmopPeopleToDatabaseWorker(resourcesForNextWorker, this.connList, this);
		return worker;
	}

	public void exec() {
		while (true) {
			synchronized (LOCK) {
				if (active.size() < numberOfWorkers) {
					WriteOmopPeopleToDatabaseWorker worker = getNextWorker();
					if (worker == null) {
						break;
					}
					active.add(worker);
					WriteOmopPeopleToDatabaseWorkerRunnable runnable = new WriteOmopPeopleToDatabaseWorkerRunnable(worker);
					ExecutorManager.getWriterExecutor().execute(runnable);
				}
			}
		}
		int activeSize = active.size();
		while (activeSize > 0) {
			TimeUtil.sleep(1);
			log.info("Almost done: " + active.size() + " active threads still running...");
			synchronized (LOCK) {
				activeSize = active.size();
				if(active.size() > 0) {
					for(WriteOmopPeopleToDatabaseWorker worker : active) {
						if(worker.getIsDone() == true) {
							log.info("WORKER NEEDS TIME TO FINISH UP ------------------------");
							TimeUtil.sleep(1);
						}
					}
				}
			}
		}
		ExecutorManager.getWriterExecutor().shutdown();
	}

	public void done(WriteOmopPeopleToDatabaseWorker worker) {
		synchronized (LOCK) {
			log.info("-----");
			log.info("Active:  " + active.size());
			log.info("Waiting: " + fileList.size());
			log.info("-----");
			active.remove(worker);
			if(active.size() == 0) {
				ExecutorManager.getWorkerExecutor().shutdown();
			}
		}
	}

}
