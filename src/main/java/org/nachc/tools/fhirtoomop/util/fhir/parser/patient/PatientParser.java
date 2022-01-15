package org.nachc.tools.fhirtoomop.util.fhir.parser.patient;

import java.util.ArrayList;
import java.util.List;

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

	public String getRaceCode() {
		return null;
	}

	//
	// methods to get collections
	//

	public List<ExtensionParser> getExtensions() {
		List<ExtensionParser> rtn = new ArrayList<ExtensionParser>();
		List<Extension> extensions = this.patient.getExtension();
		for(Extension ex : extensions) {
			ExtensionParser parser = new ExtensionParser(ex);
			rtn.add(parser);
		}
		return rtn;
	}

}
