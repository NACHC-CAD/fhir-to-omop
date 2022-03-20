package org.nachc.tools.synthea.prod;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.synthea.allpatients.GetAllPatientsForDir;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class B_GetAllSyntheaPatientsProd {

	private static File PATIENT_ID_DIR = AppParams.getPatientIdsDir_PROD();

	private static File PATIENT_DIR = AppParams.getPatientDir_PROD();
	
	public static void main(String[] args) {
		log.info("Starting download of patients");
		Timer timer = new Timer();
		FileUtil.rmdir(PATIENT_DIR);
		FileUtil.mkdirs(PATIENT_DIR);
		timer.start();
		GetAllPatientsForDir.getPatients(PATIENT_ID_DIR, PATIENT_DIR, 100);
		timer.stop();
		log.info("----------------------------");
		log.info("Time elapsed: " + timer.getElapsedString());
		log.info("----------------------------");
		log.info("Done.");
	}
	
}
