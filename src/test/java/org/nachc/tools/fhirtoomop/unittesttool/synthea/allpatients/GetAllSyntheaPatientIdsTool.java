package org.nachc.tools.fhirtoomop.unittesttool.synthea.allpatients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllSyntheaPatientIdsTool {

	private static final int CNT = 1000;
	
	public static void main(String[] args) {
		log.info("Getting " + CNT + " patients...");
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(CNT);
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
			synthea = synthea.fetchNext(cnt);
		}
		log.info("Done.");
	}
	
}
