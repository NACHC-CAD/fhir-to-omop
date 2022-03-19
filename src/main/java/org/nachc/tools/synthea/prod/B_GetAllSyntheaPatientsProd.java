package org.nachc.tools.synthea.prod;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.synthea.allpatients.GetAllPatientsForDir;

import com.nach.core.util.file.FileUtil;

public class B_GetAllSyntheaPatientsProd {

	private static File PATIENT_ID_DIR = AppParams.getPatientIdsDir_PROD();

	private static File PATIENT_DIR = AppParams.getPatientDir_PROD();
	
	public static void main(String[] args) {
		FileUtil.rmdir(PATIENT_DIR);
		FileUtil.mkdirs(PATIENT_DIR);
		GetAllPatientsForDir.getPatients(PATIENT_ID_DIR, PATIENT_DIR, 1);
	}
	
}
