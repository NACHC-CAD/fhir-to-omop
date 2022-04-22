package org.nachc.tools.fhirtoomop.fhir.patient.factory.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientResources;

public class FhirPatientResourcesAsJson implements FhirPatientResources {

	private List<InputStream> resources = new ArrayList<InputStream>();

	public FhirPatientResourcesAsJson(List<String> fileList) {
		for (String str : fileList) {
			InputStream is = new ByteArrayInputStream(str.getBytes());
			this.resources.add(is);
		}
	}

	@Override
	public List<InputStream> getResources() {
		return this.resources;
	}

}
