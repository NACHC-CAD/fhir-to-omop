package org.nachc.tools.fhirtoomop.omop.write.threaded.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

public class ExecutorManager {

	private static ThreadPoolExecutor writerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(AppParams.getMaxNumberOfThreadsForUpload());
	
	private static ThreadPoolExecutor workerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(AppParams.getMaxNumberOfThreadsForUpload());

	public static ThreadPoolExecutor getWriterExecutor() {
		return writerExecutor;
	}

	public static ThreadPoolExecutor getWorkerExecutor() {
		return workerExecutor;
	}

	
}
