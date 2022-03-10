package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.util.Properties;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

public class AppParams {

	public static final Properties PROPS = PropertiesUtil.getAsProperties("auth/app.properties");

	public static String OUT_DIR = "/test/synthea-tools";

	public static String getTestOutputDirName() {
		return PROPS.getProperty("testOutputDir");
	}

	public static File getTestOutputDir() {
		return new File(OUT_DIR);
	}

	public static File getTestOutFile(String fileName) {
		return new File(getTestOutputDir(), fileName);
	}

	public static File getFullSetOfSyntheaPatientsDir() {
		String fileName = PROPS.getProperty("fullSetOfSyntheaPatientsDir");
		File file = new File(fileName);
		return file;
	}
	
}
