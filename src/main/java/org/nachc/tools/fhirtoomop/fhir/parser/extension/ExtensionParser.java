package org.nachc.tools.fhirtoomop.fhir.parser.extension;

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

	public Coding getCoding() {
		List<Extension> extensions = ex.getExtension();
		for (Extension ex : extensions) {
			Type value = ex.getValue();
			if (value instanceof Coding == true) {
				Coding coding = (Coding) value;
				return coding;
			}
		}
		return null;
	}

	public String getCode() {
		Coding coding = getCoding();
		if (coding != null) {
			return coding.getCode();
		} else {
			return null;
		}
	}

	public String getSystem() {
		Coding coding = getCoding();
		if (coding != null) {
			return coding.getSystem();
		} else {
			return null;
		}
	}

	public String getDisplay() {
		Coding coding = getCoding();
		if (coding != null) {
			return coding.getDisplay();
		} else {
			return null;
		}
	}

	public String getAsPipeDelimited() {
		String rtn = "";
		rtn += getCode() + "|";
		rtn += getDisplay() + "|";
		rtn += getSystem();
		return rtn;
	}

}
