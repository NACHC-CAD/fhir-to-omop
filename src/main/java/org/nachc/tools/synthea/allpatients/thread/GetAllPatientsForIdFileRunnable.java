package org.nachc.tools.synthea.allpatients.thread;

import org.nachc.tools.fhirtoomop.util.synthea.oauth.SyntheaOauth;
import org.nachc.tools.synthea.allpatients.GetAllPatientsForIdFile;

public class GetAllPatientsForIdFileRunnable implements Runnable {

	private GetAllPatientsForIdFile getter;

	public GetAllPatientsForIdFileRunnable(GetAllPatientsForIdFile getter) {
		this.getter = getter;
		String token = SyntheaOauth.fetchToken();
		getter.setToken(token);
	}

	@Override
	public void run() {
		this.getter.getPatients();
	}

}
