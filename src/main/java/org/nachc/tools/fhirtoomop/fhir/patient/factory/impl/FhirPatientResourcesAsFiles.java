package org.nachc.tools.fhirtoomop.fhir.patient.factory.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;

import com.nach.core.util.file.FileUtil;

public class FhirPatientResourcesAsFiles implements FhirPatientResources {

	private List<InputStream> resources = new ArrayList<InputStream>();
	
	public FhirPatientResourcesAsFiles(List<String> fileList) {
		for(String fileName : fileList) {
			String str = FileUtil.getAsString(fileName);
			InputStream is = new ByteArrayInputStream(str.getBytes());
			this.resources.add(is);
		}
	}
	
	@Override
	public List<InputStream> getResources() {
		return this.resources;
	}

}
