package org.nachc.tools.synthea.allpatients.thread;

import org.nachc.tools.synthea.allpatients.GetAllPatientsForIdFile;

public class GetAllPatientsForIdFileRunnable implements Runnable {

	private GetAllPatientsForIdFile getter;

	public GetAllPatientsForIdFileRunnable(GetAllPatientsForIdFile getter) {
		this.getter = getter;
	}

	@Override
	public void run() {
		this.getter.getPatients();
	}

}
