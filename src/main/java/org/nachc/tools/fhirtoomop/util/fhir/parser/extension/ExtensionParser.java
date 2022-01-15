package org.nachc.tools.fhirtoomop.util.fhir.parser.extension;

import org.hl7.fhir.dstu3.model.Extension;

public class ExtensionParser {

	private Extension ex;
	
	public ExtensionParser(Extension ex) {
		this.ex = ex;
	}
	
	public String getUrl() {
		return ex.getUrl();
	}
	
	/*
	public String getCode() {
		
	}
	
	public String getSystem() {
		
	}
	
	public String getDisplay() {
		
	}
	*/

}
