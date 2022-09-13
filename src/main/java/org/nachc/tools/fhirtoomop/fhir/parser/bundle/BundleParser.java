package org.nachc.tools.fhirtoomop.fhir.parser.bundle;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Bundle.BundleLinkComponent;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.instance.model.api.IAnyResource;

import com.nach.core.util.fhir.parser.FhirJsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BundleParser implements IBundleParser {

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
			this.jsonString = bundleJson;
			this.bundle = FhirJsonParser.parse(bundleJson, Bundle.class);
		} catch (Exception exp) {
			log.error("ERROR PARSING BUNDLE: \n\n" + bundleJson);
			throw new RuntimeException(exp);
		}
	}

	public BundleParser(Bundle bundle) {
		this.bundle = bundle;
	}

	//
	// trivial getters
	//
	
	@Override
	public String getJson() {
		return this.jsonString;
	}
	
	@Override
	public Bundle getBundle() {
		return this.bundle;
	}
	
	// ---
	//
	// implementation
	//
	// ---

	@Override
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

	@Override
	public <T extends IAnyResource> T getResourceForType(Class<T> cls) {
		List<T> rtn = new ArrayList<T>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			if (resource.getClass().equals(cls)) {
				return (T) resource;
			}
		}
		return null;
	}

	@Override
	public <T extends IAnyResource> List<T> getResourceListForType(Class<T> cls) {
		List<T> rtn = new ArrayList<T>();
		List<BundleEntryComponent> entries = bundle.getEntry();
		for (BundleEntryComponent entry : entries) {
			Resource resource = entry.getResource();
			if (resource.getClass().equals(cls)) {
				rtn.add((T) resource);
			}
		}
		return rtn;
	}

	private List<BundleLinkComponent> getLinks() {
		return this.bundle.getLink();
	}

	@Override
	public String getNextUrl() {
		List<BundleLinkComponent> linkList = this.getLinks();
		if (linkList == null) {
			return null;
		}
		for (BundleLinkComponent link : linkList) {
			String rel = link.getRelation();
			if ("next".equals(rel)) {
				String url = link.getUrl();
				return url;
			}
		}
		return null;
	}

}
