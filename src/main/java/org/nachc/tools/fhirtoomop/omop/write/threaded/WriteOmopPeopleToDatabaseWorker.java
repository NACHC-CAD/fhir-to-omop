package org.nachc.tools.fhirtoomop.omop.write.threaded;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;
import org.nachc.tools.fhirtoomop.omop.write.threaded.executor.ExecutorManager;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriteOmopPeopleToDatabaseRunnable;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteOmopPeopleToDatabaseWorker {

	private WriteOmopPeopleToDatabase boss;

	private List<FhirPatientResources> resourcesList;

	private List<Connection> connectionList;

	private int done = 0;

	boolean isDone = false;

	public WriteOmopPeopleToDatabaseWorker(List<FhirPatientResources> resourcesList, List<Connection> connectionList, WriteOmopPeopleToDatabase boss) {
		this.resourcesList = resourcesList;
		this.connectionList = connectionList;
		this.boss = boss;
	}

	public void exec() {
		try {
			doExec();
		} finally {
			boss.done(this);
			this.isDone = true;
		}
	}

	public void doExec() {
		int connNumber = 0;
		for (FhirPatientResources resources : resourcesList) {
			if (connNumber >= connectionList.size() - 1) {
				connNumber = 0;
			} else {
				connNumber++;
			}
			Connection conn = connectionList.get(connNumber);
			WriteOmopPeopleToDatabaseRunnable runnable = new WriteOmopPeopleToDatabaseRunnable(this, resources, conn);
			ExecutorManager.getWorkerExecutor().execute(runnable);
		}
		while (done < resourcesList.size()) {
			TimeUtil.sleep(1);
		}
	}

	public boolean getIsDone() {
		return this.isDone;
	}

	public void done() {
		done++;
		if (done == resourcesList.size()) {
			this.isDone = true;
			log.info("DONE!");
		}
	}

}
