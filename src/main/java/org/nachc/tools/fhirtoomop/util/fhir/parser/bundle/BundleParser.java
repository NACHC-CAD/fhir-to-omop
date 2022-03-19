package org.nachc.tools.fhirtoomop.util.fhir.parser.bundle;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Bundle.BundleLinkComponent;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BundleParser {

	//
	// instance variables
	//

	private String jsonString;

	private Bundle bundle;

	//
	// constructor(s)
	//

	public BundleParser(String bundleJson) {
		try {
			// TODO: (JEG) Should probably do some validation here
			this.jsonString = bundleJson;
			this.bundle = FhirJsonParser.parse(bundleJson, Bundle.class);
		} catch(Exception exp) {
			log.error("ERROR PARSING BUNDLE: \n\n" + bundleJson);
			throw new RuntimeException(exp);
		}
	}

	public BundleParser(Bundle bundle) {
		this.bundle = bundle;
	}
	
	// ---
	//
	// implementation
	//
	// ---

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

	public <T extends Resource> T getResourceForType(T type) {
		List<T> rtn = new ArrayList<T>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			if (resource.getClass().equals(type.getClass())) {
				return (T) resource;
			}
		}
		return null;
	}
	
	public <T extends Resource> List<T> getResourceListForType(T type) {
		List<T> rtn = new ArrayList<T>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			if (resource.getClass().equals(type.getClass())) {
				rtn.add((T) resource);
			}
		}
		return rtn;
	}

	public List<BundleLinkComponent> getLinks() {
		return this.bundle.getLink();
	}
	
	public String getNextUrl() {
		List<BundleLinkComponent> linkList = this.getLinks();
		if(linkList == null) {
			return null;
		}
		for(BundleLinkComponent link : linkList) {
			String rel = link.getRelation();
			if("next".equals(rel)) {
				String url = link.getUrl();
				return url;
			}
		}
		return null;
	}
	
}
