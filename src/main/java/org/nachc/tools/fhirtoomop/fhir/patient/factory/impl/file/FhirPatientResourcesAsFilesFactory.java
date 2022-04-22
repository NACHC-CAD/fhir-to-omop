package org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;

import com.nach.core.util.file.FileUtil;

public class FhirPatientResourcesAsFilesFactory {

	public static List<FhirPatientResources> getForDir(String rootDir) {
		List<FhirPatientResources> rtn = new ArrayList<FhirPatientResources>();
		List<String> dirList = FileUtil.listResources(rootDir, FhirPatientResourcesAsFilesFactory.class);
		for (String dir : dirList) {
			List<String> fileList = FileUtil.listResources(dir, FhirPatientResourcesAsFilesFactory.class);
			FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
			rtn.add(resources);
		}
		return rtn;
	}

}
