package org.nachc.tools.synthea.allpatients;

import java.io.File;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientsForDir {

	public static void getPatients(File rootDir, File outDir, int patientsPerThread) {
		log.info("Staring test...");
		// delete the existing output file
		File[] fileList = rootDir.listFiles();
		log.info("Done getting patients for dir: " + FileUtil.getCanonicalPath(rootDir));
		for (File file : fileList) {
			String msg = "";
			msg += "\n\n\n";
			msg += "\n-----------------------------";
			msg += "\nGetting patients for file: " + FileUtil.getCanonicalPath(file);
			msg += "\n-----------------------------";
			msg += "\n\n\n";
			log.info(msg);
			GetAllPatientsForIdFile getter = new GetAllPatientsForIdFile(file, outDir, patientsPerThread);
			log.info("Getting patients...");
			getter.getPatients();
		}
		log.info("Done getting patients for dir: " + FileUtil.getCanonicalPath(rootDir));
	}

}
