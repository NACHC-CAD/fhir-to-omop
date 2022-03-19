package org.nachc.tools.synthea.prod;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.synthea.allpatientids.GetAllPatientIdsTool;

import com.nach.core.util.file.FileUtil;

public class A_GetAllSyntheaPatientIdsProd {

	private static Integer LIMIT = 2500;

	private static File PATIENT_ID_DIR = AppParams.getPatientIdsDir_PROD();

	public static void main(String[] args) {
		FileUtil.rmdir(PATIENT_ID_DIR);
		FileUtil.mkdirs(PATIENT_ID_DIR);
		GetAllPatientIdsTool.exec(PATIENT_ID_DIR, LIMIT);
	}

}
