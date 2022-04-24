package org.nachc.tools.fhirtoomop.tools.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.tools.download.authenticate.FhirServerAuthenticator;
import org.nachc.tools.fhirtoomop.tools.download.patientids.fetcher.SyntheaPatientSummaryListFetcher;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadPatientIds {

	private static final int PATIENTS_PER_FILE = 100;

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		exec(null);
	}

	public static void exec(Integer max) {
		File fhirPatientIdDir = AppParams.getFhirPatientIdDir();
		exec(fhirPatientIdDir, max);
	}

	public static void exec(File fhirPatientIdDir, Integer max) {
		log.info("Deleting and recreating fhirPatientIdDir");
		FileUtil.rmdir(fhirPatientIdDir);
		FileUtil.mkdirs(fhirPatientIdDir);
		log.info("Getting " + PATIENTS_PER_FILE + " patients...");
		log.info("Getting token...");
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher(PATIENTS_PER_FILE);
		int cnt = 0;
		int totalPatients = 0;
		boolean done = false;
		while (synthea != null) {
			cnt++;
			List<PatientSummaryParser> patientList = synthea.getPatients();
			log.debug("Got " + patientList.size() + " patients");
			File file = new File(fhirPatientIdDir, "synthea-patient-ids-" + cnt + ".txt");
			List<String> patientIds = new ArrayList<String>();
			for (PatientSummaryParser patient : patientList) {
				totalPatients++;
				if (max != null && totalPatients > max) {
					done = true;
					break;
				}
				patientIds.add(patient.getId());
			}
			FileUtil.writeCollection(patientIds, "\n", file);
			log.info("Created file: " + FileUtil.getCanonicalPath(file));
			if (cnt % 100 == 0) {
				String msg = "Getting new Token";
				FhirServerAuthenticator.refresh();
			}
			if (done == true) {
				break;
			}
			synthea = synthea.fetchNext(cnt);
		}
		log.info("Done.");
	}

}
