package org.nachc.tools.fhirtoomop.fhir.parser.bundle;

import java.util.List;

import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.instance.model.api.IBaseBundle;

public interface IBundleParser {

	String getJson();

	IBaseBundle getBundle();

	List<String> getResourceTypes();

	<T extends IAnyResource> T getResourceForType(Class<T> cls);

	<T extends IAnyResource> List<T> getResourceListForType(Class<T> cls);

	String getNextUrl();

}