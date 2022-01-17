package org.nachc.tools.fhirtoomop.unittesttool.params;

import java.io.File;
import java.util.Properties;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

public class TestParams {

	public static final Properties PROPS = PropertiesUtil.getAsProperties("app.properties");

	public static String getTestOutputDirName() {
		return PROPS.getProperty("testOutputDir");
	}

	public static String OUT_DIR = "/test/synthea-tools";

	public static File getTestOutputDir() {
		return new File(OUT_DIR);
	}
	
	public static File getTestOutFile(String fileName) {
		return new File(getTestOutputDir(), fileName);
	}

	public static String getPersonEverythingJson() {
		String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
		return json;
	}
	
	public static PatientEverythingParser getPatientEverything() {
		String json = getPersonEverythingJson();
		PatientEverythingParser everything = new PatientEverythingParser(json);
		return everything;
	}

	public static PatientParser getPatient() {
		return getPatientEverything().getPatient();
	}

}
