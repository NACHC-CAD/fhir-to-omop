package org.nachc.tools.fhirtoomop.omop.write.threaded.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

public class ExecutorManager {

	private static ThreadPoolExecutor writerExecutor;
	
	private static ThreadPoolExecutor workerExecutor;

	public static ThreadPoolExecutor getWriterExecutor() {
		if(writerExecutor == null || writerExecutor.isShutdown() == true) {
			writerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(AppParams.getMaxNumberOfThreadsForUpload());
		}
		return writerExecutor;
	}

	public static ThreadPoolExecutor getWorkerExecutor() {
		if(workerExecutor == null || workerExecutor.isShutdown() == true) {
			workerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(AppParams.getMaxNumberOfThreadsForUpload());
		}
		return workerExecutor;
	}

}
