package org.nachc.tools.fhirtoomop.util.db.write.patienteverything.thread;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import com.nach.core.util.file.FileUtil;

import ca.uhn.fhir.parser.DataFormatException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopRunnable implements Runnable {

	private Integer id;

	private Connection conn;

	private File file;

	private String json;

	private PatientEverythingParser parser;

	public WriteFhirPatientToOmopRunnable(File file, Connection conn, Integer id) {
		this.file = file;
		this.conn = conn;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			log.info("Reading file (thread " + this.id + ")");
			this.json = FileUtil.getAsString(file);
			this.parser = new PatientEverythingParser(json);
			log.info("Writing to database (thread " + this.id + ")");
			WriteFhirPatientToOmop.exec(this.parser, this.conn);
			log.info("Done writing (thread " + this.id + "): " + FileUtil.getCanonicalPath(file));
		} catch(RuntimeException exp) {
			Throwable cause = exp.getCause();
			if(cause instanceof DataFormatException) {
				log.warn("! ! ! EXCEPTION THROWN TRING TO WRITE PATIENT ! ! !");
				log.warn("This is generally expected for the data files we are using.");				
			} else {
				new RuntimeException(exp);
			}
		}
	}

}
