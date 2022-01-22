package org.nachc.tools.fhirtoomop.util.synthea.fetcher.patientsummarylist;

import java.util.List;

import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patientsummarylistbundle.PatientSummaryListBundleParser;
import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;
import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;

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
	
	public SyntheaPatientSummaryListFetcher(int howMany, String token) {
		// construct the url
		this.url = SyntheaParams.getUrl();
		url += "/Patient?";
		url += "_count=" + howMany;
		log.debug("URL: " + url);
		this.init(howMany, token);
	}

	private SyntheaPatientSummaryListFetcher(int howMany, String url, String token) {
		this.url = url;
		log.debug("URL: " + url);
		this.init(howMany, token);
	}
	
	//
	// method to get the patients from synthea
	//
	
	private String init(int howMany, String token) {
		// make the http request and get the response (json)
		this.client = new HttpRequestClient(url);
		SyntheaOauth.addHeaders(client, token);
		client.doGet();
		int status = client.getStatusCode();
		log.debug("Got status: " + status);
		this.json = client.getResponse();
		log.debug("Response length: " + json.length());
		// create the patient list
		this.patientListParser = new PatientSummaryListBundleParser(json);
		this.patientList = patientListParser.getPatientParsers();
		this.nextUrl = patientListParser.getNextUrl();
		return json;
	}
	
	public SyntheaPatientSummaryListFetcher fetchNext(int howMany, String token) {
		String nextUrl = this.getNextUrl();
		if(nextUrl == null) {
			return null;
		}
		SyntheaPatientSummaryListFetcher rtn = new SyntheaPatientSummaryListFetcher(howMany, nextUrl, token);
		return rtn;
	}
	
}
