package org.nachc.tools.fhirtoomop.util.fhir.parser.extension;

import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Extension;
import org.hl7.fhir.dstu3.model.Type;

public class ExtensionParser {

	private Extension ex;

	public ExtensionParser(Extension ex) {
		this.ex = ex;
	}

	public String getUrl() {
		return ex.getUrl();
	}

	public String getCode() {
		List<Extension> extensions = ex.getExtension();
		for (Extension ex : extensions) {
			Type value = ex.getValue();
			if (value instanceof Coding == true) {
				Coding coding = (Coding) value;
				String rtn = coding.getCode();
				return rtn;
			}
		}
		return null;
	}

	public String getSystem() {
		List<Extension> extensions = ex.getExtension();
		for (Extension ex : extensions) {
			Type value = ex.getValue();
			if (value instanceof Coding == true) {
				Coding coding = (Coding) value;
				String rtn = coding.getSystem();
				return rtn;
			}
		}
		return null;
	}

	public String getDisplay() {
		List<Extension> extensions = ex.getExtension();
		for (Extension ex : extensions) {
			Type value = ex.getValue();
			if (value instanceof Coding == true) {
				Coding coding = (Coding) value;
				String rtn = coding.getDisplay();
				return rtn;
			}
		}
		return null;
	}

}
