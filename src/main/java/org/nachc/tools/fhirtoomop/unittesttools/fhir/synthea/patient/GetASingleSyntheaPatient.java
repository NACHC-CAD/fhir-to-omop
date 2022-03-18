package org.nachc.tools.fhirtoomop.unittesttools.fhir.synthea.patient;

import com.nach.core.util.file.FileUtil;

public class GetASingleSyntheaPatient {

	public static String getAsJson() {
		String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
		return json;
	}

}
