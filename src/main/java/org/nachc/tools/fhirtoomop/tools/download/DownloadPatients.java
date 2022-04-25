package org.nachc.tools.fhirtoomop.tools.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.download.patient.download.FhirPatientDownloader;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatients {

	private static String DIR_PATH = AppParams.getDownloadPatientIdDir();

	public static void main(String[] args) {
		getPatients();
	}

	private static void getPatients() {
		log.info("Getting patients...");
		Timer timer = new Timer();
		timer.start();
		List<String> patientList = getPatientList();
		log.info("Got " + patientList.size() + " patients.");
		FhirPatientDownloader.getPatients(patientList);
		log.info("");
		log.info("-----------------------");
		log.info("start:   " + timer.getStartAsString());
		log.info("end:     " + timer.getStopAsString());
		log.info("elapsed: " + timer.getElapsedString());
		log.info("-----------------------");
		log.info("Done.");
	}

	private static List<String> getPatientList() {
		List<String> rtn = new ArrayList<String>();
		File dir = new File(DIR_PATH);
		log.info("DIR_PATH: " + DIR_PATH);
		log.info("PATIENT IDS DIR: " + FileUtil.getCanonicalPath(dir));
		List<File> fileList = FileUtil.listFiles(dir);
		for (File file : fileList) {
			rtn.addAll(FileUtil.getAsList(file));
		}
		return rtn;
	}

}
