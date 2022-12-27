package org.nachc.tools.fhirtoomop.tools.download.patientids.fetcher;

import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.parser.patientsummary.PatientSummaryParser;
import org.nachc.tools.fhirtoomop.tools.download.authenticate.FhirServerAuthenticator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

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

	private int numberOfTries;

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
		this.url = AppParams.getFhirPatientServerUrl();
		url += "/Patient?";
		url += "_count=" + howMany;
		log.debug("URL: " + url);
		this.init(howMany);
	}

	private SyntheaPatientSummaryListFetcher(int howMany, String url) {
		this.url = url;
		log.debug("URL: " + url);
		this.init(howMany);
	}

	//
	// method to get the patients from synthea
	//

	private String init(int howMany) {
		return init(howMany, false);
	}

	private String init(int howMany, boolean isError) {
		try {
			// update the error checking fields
			if (isError == false) {
				this.numberOfTries = 0;
			} else {
				this.numberOfTries++;
			}
			// make the http request and get the response (json)
			this.client = new HttpRequestClient(url);
			FhirServerAuthenticator.auth(client);
			client.doGet();
			int status = client.getStatusCode();
			log.debug("Got status: " + status);
			this.json = client.getResponse();
			log.debug("Response length: " + json.length());
			// create the patient list
			log.info("URL (attempt " + numberOfTries + "): /n" + url);
			this.patientListParser = new PatientSummaryListBundleParser(json);
			this.patientList = patientListParser.getPatientParsers();
			this.nextUrl = patientListParser.getNextUrl();
			return json;
		} catch (Exception exp) {
			String msg = "";
			msg += "\n-----------------------------------------------------------------------------------";
			msg += "\nAN EXCEPTION OCCURED, THIS IS LIKELY A TIMEOUT ERROR FROM SYNTHEA, RETRYING...";
			msg += "\nAttempt: " + this.numberOfTries;
			msg += "\n" + url;
			msg += "\n" + json;
			msg += "\nRETRYING NOW...";
			msg += "\n-----------------------------------------------------------------------------------";
			log.info("Error:" + msg);
			if (this.numberOfTries > 3) {
				log.info("GETTING A NEW TOKEN");
				String tokenMsg = "\n";
				FhirServerAuthenticator.refresh();
				FhirServerAuthenticator.auth(client);
			}
			if(this.numberOfTries > 5) {
				log.info("Could not connect to data source, giving up after 5 tries and throwing a runtime exception.");
				log.info("The end.");
				throw new RuntimeException("Giving up after 5 tries", exp);
			}
			return init(howMany, true);
		}
	}

	public SyntheaPatientSummaryListFetcher fetchNext(int howMany) {
		String nextUrl = this.getNextUrl();
		if (nextUrl == null) {
			return null;
		}
		SyntheaPatientSummaryListFetcher rtn = new SyntheaPatientSummaryListFetcher(howMany, nextUrl);
		return rtn;
	}

}
