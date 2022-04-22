package org.nachc.tools.fhirtoomop.omop.person.factory.builder.drugexposure;

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
import org.nachc.tools.omop.yaorma.dvo.DrugExposureDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopDrugExposureBuilderIntegrationTest {

	private static final String DIR_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldCreateDvo() {
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
			List<DrugExposureDvo> dvoList = omopPerson.getDrugExposureList();
			log.info("Got " + dvoList.size() + " drug exposures");
			assertTrue(dvoList.size() == 8);
			int cnt = 0;
			for (DrugExposureDvo dvo : dvoList) {
				cnt++;
				log.info("Drug Exposure " + cnt + " of " + dvoList.size());
				log.info("\tdrugExposureId:  " + dvo.getDrugExposureId());
				assertTrue(dvo.getDrugExposureId() != null);
				log.info("\tpersonId:        " + dvo.getPersonId());
				assertTrue(dvo.getPersonId() != null);
				log.info("\tvisitOccurenceId:" + dvo.getVisitOccurrenceId());
				assertTrue(dvo.getVisitOccurrenceId() != null);
				log.info("drugConceptId:     " + dvo.getDrugConceptId());
				assertTrue(dvo.getDrugConceptId() != null);
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
