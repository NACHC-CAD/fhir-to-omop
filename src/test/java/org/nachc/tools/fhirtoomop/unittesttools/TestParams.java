package org.nachc.tools.fhirtoomop.unittesttools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;

public class TestParams extends AppParams {

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
	
	public static List<String> getTestPatientAsFileList() {
		return FileUtil.listResources("/fhir/patient/everything", TestParams.class);
	}
}
