package org.nachc.tools.fhirtoomop.tools.download.patient;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsFiles;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.impl.FhirPatientResourcesAsJson;
import org.nachc.tools.fhirtoomop.tools.download.patient.fetcher.FhirPatientEverythingFetcher;
import org.nachc.tools.fhirtoomop.tools.download.patient.fetcher.FhirPatientEverythingNextFetcher;

import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirPatientEverythingFetcherIntegrationTest {

	private static final String PATIENT_ID = "5acc8bb4-2d14-4461-a560-228d96459cc3";

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		// fetch a patient
		FhirPatientEverythingFetcher fetcher = new FhirPatientEverythingFetcher();
		String json = fetcher.fetchEverything(PATIENT_ID);
		json = JsonUtil.prettyPrint(json);
		log.info("Got response: \n" + json);
		// create a FhirPatient from the response
		List<String> resourceList = new ArrayList<String>();
		resourceList.add(json);
		FhirPatient fhirPatient;
		String patientId;
		FhirPatientResourcesAsJson resources = new FhirPatientResourcesAsJson(resourceList);
		fhirPatient = new FhirPatientFactory(resources).buildFromJson();
		// assert that we got the right patient
		patientId = fhirPatient.getPatientId();
		log.info("Created fhirPatient: " + patientId);
		assertTrue(patientId.equals(PATIENT_ID));
		// get the next url and the next page for the patient
		BundleParser bundle = new BundleParser(json);
		String nextUrl = bundle.getNextUrl();
		FhirPatientEverythingNextFetcher nextFetcher = new FhirPatientEverythingNextFetcher();
		String pageTwoJson = nextFetcher.fetchNext(nextUrl);
		// create a FhirPerson from the two responses
		resourceList.add(pageTwoJson);
		resources = new FhirPatientResourcesAsJson(resourceList);
		fhirPatient = new FhirPatientFactory(resources).buildFromJson();
		// assert that we got the right patient
		patientId = fhirPatient.getPatientId();
		log.info("Created fhirPatient: " + patientId);
		assertTrue(patientId.equals(PATIENT_ID));
		// done
		log.info("Done.");
	}

}
