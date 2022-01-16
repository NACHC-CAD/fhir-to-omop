package org.nachc.tools.fhirtoomop.util.fhir.parser.patient;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.Extension;
import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.util.fhir.parser.extension.ExtensionParser;

public class PatientParser {

	private Patient patient;

	public PatientParser(Patient patient) {
		this.patient = patient;
	}

	public String getId() {
		String patientId = patient.getIdElement().getIdPart();
		return patientId;
	}

	public Coding getRace() {
		ExtensionParser ex = getExtension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
		Coding rtn = ex.getCoding();
		return rtn;
	}

	public Coding getEthnicity() {
		ExtensionParser ex = getExtension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");
		Coding rtn = ex.getCoding();
		return rtn;
	}

	public AdministrativeGender getGender() {
		AdministrativeGender ag = this.patient.getGender();
		return ag;
	}

	//
	// methods for extensions
	//

	public List<ExtensionParser> getExtensions() {
		List<ExtensionParser> rtn = new ArrayList<ExtensionParser>();
		List<Extension> extensions = this.patient.getExtension();
		for (Extension ex : extensions) {
			ExtensionParser parser = new ExtensionParser(ex);
			rtn.add(parser);
		}
		return rtn;
	}

	public ExtensionParser getExtension(String url) {
		List<ExtensionParser> extensions = getExtensions();
		for (ExtensionParser ex : extensions) {
			if (url != null && url.equals(ex.getUrl())) {
				return ex;
			}
		}
		return null;
	}

	public String getExtensionAsPipeDelimited(String url) {
		ExtensionParser ex = getExtension(url);
		String rtn = ex.getAsPipeDelimited();
		return rtn;
	}

}
