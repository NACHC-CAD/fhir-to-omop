package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseRunnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseWorker {

	private WriteOmopPeopleToDatabase boss;

	private List<FhirPatientResources> resourcesList;

	private List<Connection> connectionList;

	private List<Thread> threads = new ArrayList<Thread>();
	
	public WriteOmopPeopleToDatabaseWorker(List<FhirPatientResources> resourcesList, List<Connection> connectionList, WriteOmopPeopleToDatabase boss) {
		this.resourcesList = resourcesList;
		this.connectionList = connectionList;
		this.boss = boss;
	}

	public void exec() {
		int connNumber = 0;
		for (FhirPatientResources resources : resourcesList) {
			if (connNumber >= connectionList.size() - 1) {
				connNumber = 0;
			} else {
				connNumber++;
			}
			Connection conn = connectionList.get(connNumber);
			WriteOmopPeopleToDatabaseRunnable runnable = new WriteOmopPeopleToDatabaseRunnable(resources, conn);
			Thread thread = new Thread(runnable);
			threads.add(thread);
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception exp) {
				throw new RuntimeException(exp);
			}
		}
		boss.done(this);
	}

}
