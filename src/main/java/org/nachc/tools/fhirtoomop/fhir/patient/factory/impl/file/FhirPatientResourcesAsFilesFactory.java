package org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;

import com.nach.core.util.file.FileUtil;

public class FhirPatientResourcesAsFilesFactory {

	public static List<FhirPatientResources> getForDir(String rootDir) {
		List<FhirPatientResources> rtn = new ArrayList<FhirPatientResources>();
		List<String> dirList = FileUtil.listResources(rootDir, FhirPatientResourcesAsFilesFactory.class);
		return getForList(dirList);
	}
	
	public static List<FhirPatientResources> getForList(List<String> dirList) {
		List<FhirPatientResources> rtn = new ArrayList<FhirPatientResources>();
		for (String dir : dirList) {
			List<String> fileList = FileUtil.listResources(dir, FhirPatientResourcesAsFilesFactory.class);
			FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
			rtn.add(resources);
		}
		return rtn;
	}

	public static FhirPatientResources getForPatient(String dirName) {
		if(dirName.endsWith(".json")) {
			List<String> fileList = new ArrayList<String>();
			fileList.add(dirName);
			FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
			return resources;
		} else {
			List<String> fileList = FileUtil.listResources(dirName, FhirPatientResourcesAsFilesFactory.class);
			FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
			return resources;
		}
	}

	public static FhirPatientResources getForPatient(File dir) {
		List<String> fileList = FileUtil.listResources(dir);
		FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
		return resources;
	}
	
	
}
