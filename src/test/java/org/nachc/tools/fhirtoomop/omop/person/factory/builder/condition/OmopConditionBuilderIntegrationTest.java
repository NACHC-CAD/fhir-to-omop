package org.nachc.tools.fhirtoomop.omop.person.factory.builder.condition;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.omop.yaorma.dvo.ConditionOccurrenceDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopConditionBuilderIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetDvo() {
		log.info("Starting test...");
		List<String> fileList = FileUtil.listResources(DIR_PATH, getClass());
		FhirPatientResourcesAsFiles resources = new FhirPatientResourcesAsFiles(fileList);
		FhirPatient fhirPatient = new FhirPatientFactory(resources).buildFromFileList();
		// get a connection
		log.info("Getting connection...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		log.info("Got connection");
		try {
			OmopPerson omopPerson = new OmopPersonFactory().build(fhirPatient, conn);
			List<ConditionOccurrenceDvo> conditionList = omopPerson.getConditionOccurrenceList();
			log.info("Got " + conditionList.size() + " conditions.");
			assertTrue(conditionList.size() == 10);
			ConditionOccurrenceDvo dvo = conditionList.get(0);
			log.info("id:          " + dvo.getConditionOccurrenceId());
			log.info("startDate:   " + dvo.getConditionStartDate());
			log.info("endDate:     " + dvo.getConditionEndDate());
			log.info("sourceValue: " + dvo.getConditionSourceValue());
			log.info("conceptId:   " + dvo.getConditionConceptId());
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
