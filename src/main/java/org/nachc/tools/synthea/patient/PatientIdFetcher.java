package org.nachc.tools.synthea.patient;

import org.hl7.fhir.dstu3.model.Patient;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummarylistbundle.PatientSummaryListBundleParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

public class PatientIdFetcher {

	public static String getASinglePatientId(String token) {
		String patientJson = new SyntheaPatientSummaryListFetcher(1, token).getJson();
		Patient fhirPatient = new PatientSummaryListBundleParser(patientJson).getPatients().get(0);
		PatientSummaryParser patient = new PatientSummaryParser(fhirPatient);
		String patientId = patient.getId();
		return patientId;
	}

}
