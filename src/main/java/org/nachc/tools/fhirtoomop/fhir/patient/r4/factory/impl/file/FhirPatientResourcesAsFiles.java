package org.nachc.tools.fhirtoomop.fhir.patient.r4.factory.impl.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;

import com.nach.core.util.file.FileUtil;

public class FhirPatientResourcesAsFiles implements FhirPatientResources {

	private List<String> fileList;
	
	private List<InputStream> resources;
	
	public FhirPatientResourcesAsFiles(List<String> fileList) {
		this.fileList = fileList;
	}
	
	@Override
	public List<InputStream> getResources() {
		if(this.resources == null) {
			this.resources = new ArrayList<InputStream>();
			for(String fileName : fileList) {
				String str = FileUtil.getAsString(fileName);
				InputStream is = new ByteArrayInputStream(str.getBytes());
				this.resources.add(is);
			}
		}
		return this.resources;
	}

}
