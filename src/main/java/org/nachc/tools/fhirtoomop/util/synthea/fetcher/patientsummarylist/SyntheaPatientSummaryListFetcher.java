package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummarylistbundle.PatientSummaryListBundleParser;
import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;

import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class implements the ability to fetch a list of patient summaries from
 * the synthia fhir server.
 *
 */

@Slf4j
public class SyntheaPatientSummaryListFetcher {

	//
	// instance variables
	//
	
	private HttpRequestClient client;
	
	private String url;
	
	private String json;

	private PatientSummaryListBundleParser patientListParser;
	
	private List<PatientSummaryParser> patientList;
	
	private String nextUrl;

	//
	// getters
	//
	
	public HttpRequestClient getClient() {
		return this.client;
	}

	public String getUrl() {
		return this.url;
	}
	
	public String getNextUrl() {
		return this.nextUrl;
	}
	
	public String getJson() {
		return this.json;
	}
	
	public PatientSummaryListBundleParser getParser() {
		return this.patientListParser;
	}
	
	public List<PatientSummaryParser> getPatients() {
		return this.patientList;
	}
	
	public int getStatusCode() {
		return this.client.getStatusCode();
	}

	//
	// constructor
	//
	
	public SyntheaPatientSummaryListFetcher(int howMany) {
		// construct the url
		this.url = SyntheaParams.getUrl();
		String key = SyntheaParams.getKey();
		url += "/Patient?";
		url += "_count=" + howMany;
		url += "&apikey=" + key;
		log.info("URL: " + url);
		this.fetchPatients(howMany);
	}

	private SyntheaPatientSummaryListFetcher(int howMany, String url) {
		String key = SyntheaParams.getKey();
		this.url = url;
		this.url += "&apikey=" + key;
		log.info("URL: " + url);
		this.fetchPatients(howMany);
	}
	
	//
	// method to get the patients from synthea
	//
	
	private String fetchPatients(int howMany) {
		// make the http request and get the response (json)
		this.client = new HttpRequestClient(url);
		client.doGet();
		int status = client.getStatusCode();
		log.info("Got status: " + status);
		this.json = client.getResponse();
		log.info("Response length: " + json.length());
		// create the patient list
		this.patientListParser = new PatientSummaryListBundleParser(json);
		this.patientList = patientListParser.getPatientParsers();
		this.nextUrl = patientListParser.getNextUrl();
		return json;
	}
	
	public SyntheaPatientSummaryListFetcher fetchNext(int howMany) {
		String nextUrl = this.getNextUrl();
		SyntheaPatientSummaryListFetcher rtn = new SyntheaPatientSummaryListFetcher(howMany, nextUrl);
		return rtn;
	}
	
}
