package org.nachc.tools.synthea.allpatientids;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllPatientIdsTool {

	private static final int PATIENTS_PER_FILE = 1000;

	public static void exec() {
		exec(null);
	}

	// TODO: MAKE AppParams optional
	
	public static void exec(Integer max) {
		log.info("Deleting and recreating fhirPatientIdDir");
		File fhirPatientIdDir = AppParams.getFhirPatientIdDir();
		FileUtil.rmdir(fhirPatientIdDir);
		FileUtil.mkdirs(fhirPatientIdDir);
		log.info("Getting " + PATIENTS_PER_FILE + " patients...");
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(PATIENTS_PER_FILE, token);
		int cnt = 0;
		int totalPatients = 0;
		boolean done = false;
		while (synthea != null) {
			cnt++;
			List<PatientSummaryParser> patientList = synthea.getPatients();
			log.debug("Got " + patientList.size() + " patients");
			File file = new File(fhirPatientIdDir,"synthea-patient-ids-" + cnt + ".txt");
			List<String> patientIds = new ArrayList<String>();
			for (PatientSummaryParser patient : patientList) {
				totalPatients++;
				if(max != null && totalPatients > max) {
					done = true;
					break;
				}
				patientIds.add(patient.getId());
			}
			FileUtil.writeCollection(patientIds, "\n", file);
			log.info("Created file: " + FileUtil.getCanonicalPath(file));
			if (cnt % 100 == 0) {
				String msg = "Getting new Token";
				String newToken = SyntheaOauth.fetchToken();
				msg += "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
				msg += "\nGenerating new token";
				msg += "\nOLD: " + token;
				msg += "\nNEW: " + newToken;
				msg += "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
				log.info(msg);
				token = newToken;
			}
			if(done == true) {
				break;
			}
			synthea = synthea.fetchNext(cnt, token);
		}
		log.info("Done.");
	}

}
