package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseRunnable;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseWorkerRunnable;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabase {

	private static final Object LOCK = new Object();
	
	private static int numberOfWorkers;
	
	private static List<WriteOmopPeopleToDatabaseWorker> waiting = new ArrayList<WriteOmopPeopleToDatabaseWorker>();

	private static List<WriteOmopPeopleToDatabaseWorker> active = new ArrayList<WriteOmopPeopleToDatabaseWorker>();
	
	private static List<Thread> threads = new ArrayList<Thread>();

	public static void exec(List<FhirPatientResources> resourceList, List<Connection> connList, int maxNumberOfWorkers, int numberOfPatientsPerWorker) {
		init(resourceList, connList, maxNumberOfWorkers, numberOfPatientsPerWorker);
		exec();
	}
	
	private static void init(List<FhirPatientResources> resourceList, List<Connection> connList, int maxNumberOfWorkers, int numberOfPatientsPerWorker) {
		log.info("Creating workers");
		numberOfWorkers = maxNumberOfWorkers;
		List<FhirPatientResources> resourcesForWorker = new ArrayList<FhirPatientResources>();
		for (int i = 0; i < resourceList.size(); i++) {
			resourcesForWorker.add(resourceList.get(i));
			if (i % numberOfPatientsPerWorker == 0) {
				if(i != 0) {
					WriteOmopPeopleToDatabaseWorker worker = new WriteOmopPeopleToDatabaseWorker(resourcesForWorker, connList);
					waiting.add(worker);
				}
				resourcesForWorker = new ArrayList<FhirPatientResources>();
			}
		}
		if(resourcesForWorker.size() > 0) {
			WriteOmopPeopleToDatabaseWorker worker = new WriteOmopPeopleToDatabaseWorker(resourcesForWorker, connList);
			waiting.add(worker);
		}
	}

	private static void exec() {
		while(waiting.size() > 0) {
			synchronized (LOCK) {
				if(active.size() < numberOfWorkers + 1) {
					log.info("-----");
					log.info("Active:  " + active.size());
					log.info("Waiting: " + waiting.size());
					log.info("-----");
					WriteOmopPeopleToDatabaseWorker worker = waiting.remove(0);
					active.add(worker);
					WriteOmopPeopleToDatabaseWorkerRunnable runnable = new WriteOmopPeopleToDatabaseWorkerRunnable(worker);
					Thread thread = new Thread(runnable);
					threads.add(thread);
					thread.start();
				}
			}
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception exp) {
				log.info("Could not join thread");
			}
		}
	}

	public static void done(WriteOmopPeopleToDatabaseWorker worker) {
		synchronized (LOCK) {
			log.info("-----");
			log.info("Active:  " + active.size());
			log.info("Waiting: " + waiting.size());
			log.info("-----");
			active.remove(worker);
		}
	}

}
