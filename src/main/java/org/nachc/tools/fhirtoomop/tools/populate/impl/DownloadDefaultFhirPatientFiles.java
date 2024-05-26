package org.nachc.tools.fhirtoomop.tools.populate.impl;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

public class DownloadDefaultFhirPatientFiles {

	public static void exec() {
		String url = AppParams.get("syntheaDefaultTestFhirPatientsUrl");
	}
	
}
