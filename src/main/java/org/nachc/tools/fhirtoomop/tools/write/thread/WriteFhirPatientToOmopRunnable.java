package org.nachc.tools.fhirtoomop.tools.write.thread;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.write.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopRunnable implements Runnable {

	private Connection conn;

	private File file;

	private String json;
	
	private PatientEverythingParser parser;

	public WriteFhirPatientToOmopRunnable(File file, Connection conn) {
		this.file = file;
		this.conn = conn;
	}
	
	@Override
	public void run() {
		this.json = FileUtil.getAsString(file);
		this.parser = new PatientEverythingParser(json);
		WriteFhirPatientToOmop.exec(this.parser, this.conn);
		log.info("Done writing for: " + FileUtil.getCanonicalPath(file));
	}

}
