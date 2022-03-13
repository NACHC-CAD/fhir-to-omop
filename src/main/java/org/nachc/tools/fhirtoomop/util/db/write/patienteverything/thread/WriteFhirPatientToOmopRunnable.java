package org.nachc.tools.fhirtoomop.util.db.write.patienteverything.thread;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteFhirPatientToOmop;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;
import org.nachc.tools.fhirtoomop.util.fhirtoomop.person.OmopPersonEverythingFactory;

import com.nach.core.util.file.FileUtil;

import ca.uhn.fhir.parser.DataFormatException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopRunnable implements Runnable {

	private Integer id;

	private Connection conn;

	private File file;

	private String filePath;
	
	private String filePathShortend;

	private String json;

	private PatientEverythingParser parser;

	public WriteFhirPatientToOmopRunnable(File file, Connection conn, Integer id) {
		this.file = file;
		this.conn = conn;
		this.id = id;
		this.filePath = FileUtil.getCanonicalPath(file);
		this.filePathShortend = filePath;
		if(this.filePath != null && this.filePath.indexOf('/') > 0) {
			this.filePathShortend = filePath.substring(filePath.lastIndexOf('/') + filePath.length());
		}
	}

	public WriteFhirPatientToOmopRunnable(String filePath, String json, Connection conn, Integer id) {
		this.filePath = filePath;
		this.json = json;
		this.conn = conn;
		this.id = id;
		this.filePathShortend = filePath;
		if(this.filePath != null && this.filePath.indexOf('/') > 0) {
			this.filePathShortend = filePath.substring(filePath.lastIndexOf('/') + filePath.length());
		}
	}

	@Override
	public void run() {
		try {
			// get the json if we only have a file
			if (this.json == null) {
				this.json = FileUtil.getAsString(file);
			}
			// parse the json
			this.parser = new PatientEverythingParser(json);
			OmopPersonEverythingFactory personEverything = new OmopPersonEverythingFactory(this.parser, this.conn);
			log.info("DONE: Parsing fhir resource (" + this.id + ")" + this.filePathShortend);
			// write to the database
			WriteFhirPatientToOmop.exec(personEverything, this.conn);
			log.info("DONE: Writing to database (thread " + this.id + ")" + filePathShortend);
		} catch (RuntimeException exp) {
			Throwable cause = exp.getCause();
			if (cause instanceof DataFormatException) {
				log.warn("! ! ! EXCEPTION THROWN TRING TO WRITE PATIENT ! ! !");
				log.warn("File: " + filePath);
				log.warn("This is generally expected for the data files we are using.");
			} else {
				// TODO: (JEG) CHECK TO SEE IF THIS CODE IS REACHED (SET A BREAK POINT)
				throw new RuntimeException(exp);
			}
		}
	}

}
