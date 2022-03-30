package org.nachc.tools.fhirtoomop.omop.person.factory;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.condition.OmopConditionOccurrenceBuilder;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.drugexposure.OmopDrugExposureBuilder;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.observation.OmopObservationBuilder;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.person.OmopPersonBuilder;
import org.nachc.tools.fhirtoomop.omop.person.factory.builder.visitoccurrence.OmopVisitOccurrenceBuilder;

public class OmopPersonFactory {

	public OmopPerson build(FhirPatient fhirPatient, Connection conn) {
		OmopPerson rtn = new OmopPerson();
		new OmopPersonBuilder(fhirPatient, rtn, conn).build();
		new OmopVisitOccurrenceBuilder(fhirPatient, rtn, conn).build();
		new OmopConditionOccurrenceBuilder(fhirPatient, rtn, conn).build();
		new OmopDrugExposureBuilder(fhirPatient, rtn, conn).build();
		new OmopObservationBuilder(fhirPatient, rtn, conn).build();
		return rtn;
	}

}
