package org.nachc.tools.fhirtoomop.unittesttool.synthea.allpatients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllSyntheaPatientIdsTool {

	private static final int CNT = 1000;
	
	public static void main(String[] args) {
		log.info("Getting " + CNT + " patients...");
		log.info("Getting token...");
		String token = SyntheaOauth.fetchToken();
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(CNT, token);
		int cnt = 0;
		while(synthea != null) {
			cnt++;
			List<PatientSummaryParser> patientList = synthea.getPatients();
			log.debug("Got " + patientList.size() + " patients");
			File file = TestParams.getTestOutFile("/all-patient-ids/synthea-patient-ids-" + cnt + ".txt");
			List<String> patientIds = new ArrayList<String>();
			for(PatientSummaryParser patient : patientList) {
				patientIds.add(patient.getId());
			}
			FileUtil.writeCollection(patientIds, "\n", file);
			log.info("Created file: " + FileUtil.getCanonicalPath(file));
			if(cnt % 100 == 0) {
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
			synthea = synthea.fetchNext(cnt, token);
		}
		log.info("Done.");
	}
	
}
