package org.nachc.tools.fhirtoomopexamples.synthea;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.synthea.allpatients.GetAllPatientsForDir;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientsForDirManualTest {

	private static final String SOURCE_FILE_DIR = "C:\\test\\synthea-tools\\all-patient-ids";

	private static final String OUT_FILE_DIR = "/test-patients/_PROD";

	private static final int PATIENTS_PER_THREAD = 10;

	@Test
	public void shouldGetPatients() {
		log.info("Staring test...");
		// delete the existing output file
		File outDir = AppParams.getTestOutputDir();
		outDir = new File(outDir, OUT_FILE_DIR);
		File rootDir = new File(SOURCE_FILE_DIR);
		// delete the existing output file
		log.info("Deleting existing out file: " + FileUtil.getCanonicalPath(outDir));
		FileUtil.rmdir(outDir);
		FileUtil.mkdirs(outDir);
		// get the patients
		log.info("Getting patients...");
		GetAllPatientsForDir.getPatients(rootDir, outDir, PATIENTS_PER_THREAD);
		log.info("Done.");
	}

}
