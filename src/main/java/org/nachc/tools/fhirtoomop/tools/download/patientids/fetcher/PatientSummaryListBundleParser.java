package org.nachc.tools.fhirtoomop.tools.download.patientids.fetcher;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.IBundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.patientsummary.PatientSummaryParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientSummaryListBundleParser {

	private String jsonString;

	private Bundle bundle;

	private IBundleParser bundleParser;

	private String nextUrl;

	public PatientSummaryListBundleParser(String bundleJson) {
		this.jsonString = bundleJson;
		this.bundle = FhirJsonParser.parse(bundleJson, Bundle.class);
		this.bundleParser = new BundleParser(bundle);
		this.nextUrl = bundleParser.getNextUrl();
	}

	//
	// trivial getters and setters
	//

	public String getJsonString() {
		return this.jsonString;
	}

	public String getNextUrl() {
		return this.nextUrl;
	}

	//
	// implementation
	//

	public List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			ResourceType type = entry.getResource().getResourceType();
			Resource resource = entry.getResource();
			if (resource instanceof Patient) {
				patients.add((Patient) resource);
			}
		}
		return patients;
	}

	public List<PatientSummaryParser> getPatientParsers() {
		List<PatientSummaryParser> patients = new ArrayList<PatientSummaryParser>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			ResourceType type = entry.getResource().getResourceType();
			Resource resource = entry.getResource();
			if (resource instanceof Patient) {
				PatientSummaryParser parser = new PatientSummaryParser((Patient) resource);
				patients.add(parser);
			}
		}
		return patients;
	}

	public List<String> getResourceTypes() {
		List<String> types = new ArrayList<String>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			String type = resource.getClass().getName();
			types.add(type);
		}
		return types;
	}

}
