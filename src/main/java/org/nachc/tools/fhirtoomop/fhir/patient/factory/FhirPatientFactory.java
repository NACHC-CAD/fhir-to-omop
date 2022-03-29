package org.nachc.tools.fhirtoomop.fhir.patient.factory;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Condition;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;
import org.nachc.tools.fhirtoomop.fhir.parser.condition.ConditionParser;
import org.nachc.tools.fhirtoomop.fhir.parser.encounter.EncounterParser;
import org.nachc.tools.fhirtoomop.fhir.parser.patient.PatientParser;
import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;

import com.nach.core.util.file.FileUtil;

public class FhirPatientFactory {

	private List<BundleParser> bundleParserList = new ArrayList<BundleParser>();

	public FhirPatientFactory(List<String> resourceList) {
		for (String path : resourceList) {
			String json = FileUtil.getAsString(path);
			BundleParser bundleParser = new BundleParser(json);
			this.bundleParserList.add(bundleParser);
		}
	}

	public FhirPatient buildFhirPatient() {
		FhirPatient rtn = new FhirPatient();
		buildPatient(rtn);
		buildEncounterList(rtn);
		buildConditionList(rtn);
		return rtn;
	}

	// ---
	//
	// all private past here
	//
	// ---

	private void buildPatient(FhirPatient rtn) {
		for (BundleParser parser : this.bundleParserList) {
			Patient patient = parser.getResourceForType(Patient.class);
			if (patient != null) {
				rtn.setPatient(new PatientParser(patient));
				return;
			}
		}
	}

	private void buildEncounterList(FhirPatient rtn) {
		for (BundleParser parser : this.bundleParserList) {
			List<Encounter> list = parser.getResourceListForType(Encounter.class);
			for (Encounter enc : list) {
				rtn.getEncounterList().add(new EncounterParser(enc));
			}
		}
	}

	private void buildConditionList(FhirPatient rtn) {
		for (BundleParser parser : this.bundleParserList) {
			List<Condition> list = parser.getResourceListForType(Condition.class);
			for (Condition con : list) {
				rtn.getConditionList().add(new ConditionParser(con));
			}
		}
	}

}