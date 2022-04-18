package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.nachc.tools.fhirtoomop.omop.write.listofpatients.allatonce.WriteListOfFhirPatientsToOmopAllAtOnce;
import org.nachc.tools.fhirtoomop.omop.write.threaded.runnable.WriterRunnable;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class should be used to write large lists of FHIR patients to an omop
 * database.
 *
 */
@Slf4j
public class WriteListOfFhirPatientsToOmop {

	private static int maxActive = AppParams.getMaxNumberOfWorkersForUpload();

	private static List<WriteListOfFhirPatientsToOmopAllAtOnce> waiting = new ArrayList<WriteListOfFhirPatientsToOmopAllAtOnce>();

	private static List<WriteListOfFhirPatientsToOmopAllAtOnce> active = new ArrayList<WriteListOfFhirPatientsToOmopAllAtOnce>();

	private static HashMap<WriteListOfFhirPatientsToOmopAllAtOnce, Thread> threadMap = new HashMap<WriteListOfFhirPatientsToOmopAllAtOnce, Thread>();

	private static List<Thread> activeThreads = new ArrayList<Thread>();
	
	public static void exec(List<String> fileList, List<Connection> connList, int maxThreads) {
		ArrayList<String> filesToWrite = new ArrayList<String>();
		int cnt = 0;
		// add everything to waiting
		for (int i = 0; i < fileList.size(); i++) {
			filesToWrite.add(fileList.get(i));
			if (i % maxThreads == 0 && i != 0) {
				WriteListOfFhirPatientsToOmopAllAtOnce writer = new WriteListOfFhirPatientsToOmopAllAtOnce(filesToWrite, connList);
				waiting.add(writer);
				cnt++;
				filesToWrite = new ArrayList<String>();
				logMsg(i, cnt);
			}
		}
		while (active.size() < maxActive && waiting.size() > 0) {
			WriteListOfFhirPatientsToOmopAllAtOnce writer = waiting.remove(0);
			WriterRunnable runnable = new WriterRunnable(writer);
			Thread thread = new Thread(runnable);
			active.add(writer);
			activeThreads.add(thread);
			threadMap.put(writer, thread);
			thread.start();
			TimeUtil.sleep(1);
		}
		for(Thread thread : activeThreads) {
			try {
				thread.join();
			} catch(Exception exp) {
				log.info("Exception thrown joining thread.");
			}
		}
	}

	public static void done(WriteListOfFhirPatientsToOmopAllAtOnce writer) {
		log.info("Doing Done...");
		active.remove(writer);
		Thread thread = threadMap.remove(writer);
		activeThreads.remove(thread);
		log.info("-------------");
		log.info("ACTIVE:  " + active.size());
		log.info("WAITING: " + waiting.size());
		log.info("-------------");
	}

	private static void logMsg(int i, int cnt) {
		String msg = "";
		msg += "\n\n\n\n------------------------------------------------";
		msg += "\nWriting Datafiles";
		msg += "\nTotal written: " + i;
		msg += "\nBlock count:   " + cnt;
		msg += "\n------------------------------------------------\n\n\n";
		log.info(msg);
	}

}
