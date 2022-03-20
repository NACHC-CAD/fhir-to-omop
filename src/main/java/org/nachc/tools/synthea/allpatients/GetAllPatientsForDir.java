package org.nachc.tools.synthea.allpatients;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import org.nachc.tools.synthea.allpatients.thread.GetAllPatientsForIdFileRunnable;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientsForDir {

	private static int MAX_ACTIVE = 100;
	
	private static ArrayList<GetAllPatientsForIdFile> waiting = new ArrayList<GetAllPatientsForIdFile>();

	private static ArrayList<GetAllPatientsForIdFile> activeGetters = new ArrayList<GetAllPatientsForIdFile>();
	
	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	public static void getPatients(File rootDir, File outDir, int patientsPerThread) {
		log.info("Getting patients");
		File[] fileList = rootDir.listFiles();
		log.info("Done getting patients for dir: " + FileUtil.getCanonicalPath(rootDir));
		// put all of the files in the waiting queue
		for (File file : fileList) {
			GetAllPatientsForIdFile getter = new GetAllPatientsForIdFile(file, outDir, patientsPerThread);
			waiting.add(getter);
		}
		while(waiting.size() > 0) {
			while(activeGetters.size() <= MAX_ACTIVE && waiting.size() > 0) {
				GetAllPatientsForIdFile getter = waiting.remove(0);
				GetAllPatientsForIdFileRunnable runnable = new GetAllPatientsForIdFileRunnable(getter);
				Thread thread = new Thread(runnable);
				threads.add(thread);
				activeGetters.add(getter);
				thread.start();
			}
			log.info("----------------------");
			log.info("ACTIVE:  " + activeGetters.size());
			log.info("WAITING: " + waiting.size());
			TimeUtil.sleep(10);
		}
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch(Exception exp) {
				log.info("Could not join thread");
			}
		}
		log.info("Done getting patients for dir: " + FileUtil.getCanonicalPath(rootDir));
		log.info("Done.");
	}

	public static synchronized void done(GetAllPatientsForIdFile getter) {
		log.info("REMOVING GETTER");
		activeGetters.remove(getter);
		log.info("GETTER REMOVED");
	}
	
}
