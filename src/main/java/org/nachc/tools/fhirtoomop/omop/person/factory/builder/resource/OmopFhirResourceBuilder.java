package org.nachc.tools.fhirtoomop.omop.person.factory.builder.resource;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;

public class OmopFhirResourceBuilder {

	public OmopPerson omopPerson;

	public FhirPatient fhirPatient;
	
	private Connection conn;

	public OmopFhirResourceBuilder(FhirPatient fhirPatient, OmopPerson omopPerson, Connection conn) {
		this.omopPerson = omopPerson;
		this.fhirPatient = fhirPatient;
		this.conn = conn;
	}

	public void build() {
		List<String> resourceList = this.fhirPatient.getResourceTypes();
		this.omopPerson.setResourceList(resourceList);
	}

}
