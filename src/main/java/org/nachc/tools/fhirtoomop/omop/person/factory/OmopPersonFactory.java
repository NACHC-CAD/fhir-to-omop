package org.nachc.tools.fhirtoomop.omop.person.factory;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.person.OmopPersonBuilder;

public class OmopPersonFactory {

	public OmopPerson build(FhirPatient fhirPatient, Connection conn) {
		OmopPerson rtn = new OmopPerson();
		new OmopPersonBuilder(fhirPatient, rtn, conn).build();
		return rtn;
	}

}
