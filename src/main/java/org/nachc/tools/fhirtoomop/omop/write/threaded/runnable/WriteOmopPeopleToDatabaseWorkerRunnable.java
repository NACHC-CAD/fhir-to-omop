package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import org.nachc.tools.fhirtoomop.omop.write.threaded.WriteOmopPeopleToDatabaseWorker;

public class WriteOmopPeopleToDatabaseWorkerRunnable implements Runnable {

	private WriteOmopPeopleToDatabaseWorker worker;
	
	public WriteOmopPeopleToDatabaseWorkerRunnable(WriteOmopPeopleToDatabaseWorker worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
		worker.exec();
	}

}
