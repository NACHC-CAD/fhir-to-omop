package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import org.nachc.tools.fhirtoomop.omop.write.listofpatients.allatonce.WriteListOfFhirPatientsToOmopAllAtOnce;

public class WriterRunnable implements Runnable {

	private WriteListOfFhirPatientsToOmopAllAtOnce writer;
	
	public WriterRunnable(WriteListOfFhirPatientsToOmopAllAtOnce writer) {
		this.writer = writer;
	}
	
	@Override
	public void run() {
		writer.exec();
	}

}
