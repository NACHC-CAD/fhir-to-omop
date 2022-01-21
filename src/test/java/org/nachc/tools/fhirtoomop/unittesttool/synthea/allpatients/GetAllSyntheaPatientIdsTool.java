package org.nachc.tools.fhirtoomop.unittesttool.synthea.allpatients;

import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist.SyntheaPatientSummaryListFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllSyntheaPatientIdsTool {

	private static final int CNT = 1000;
	
	public static void main(String[] args) {
		log.info("Getting " + CNT + " patients...");
		SyntheaPatientSummaryListFetcher synthea = new SyntheaPatientSummaryListFetcher();
		List<PatientSummaryParser> patientList = synthea.fetchPatientSummaryParsers(CNT);
		log.info("Got " + patientList.size() + " patients");
		
		log.info("Done.");
	}
	
}
