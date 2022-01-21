package org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummarylistbundle;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientSummaryListBundleParser {

	private String jsonString;

	private Bundle bundle;

	public PatientSummaryListBundleParser(String bundleJson) {
		this.jsonString = bundleJson;
		this.bundle = FhirJsonParser.parse(bundleJson, Bundle.class);
	}

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
