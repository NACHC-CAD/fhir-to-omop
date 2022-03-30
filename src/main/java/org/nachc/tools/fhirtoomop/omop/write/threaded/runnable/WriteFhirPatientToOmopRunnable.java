package org.nachc.tools.fhirtoomop.omop.write.threaded.runnable;

import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.fhir.patient.FhirPatient;
import org.nachc.tools.fhirtoomop.fhir.patient.factory.FhirPatientFactory;
import org.nachc.tools.fhirtoomop.omop.person.OmopPerson;
import org.nachc.tools.fhirtoomop.omop.person.factory.OmopPersonFactory;
import org.nachc.tools.fhirtoomop.omop.write.singlepatient.WriteOmopPersonToDatabase;

import com.nach.core.util.file.FileUtil;

import ca.uhn.fhir.parser.DataFormatException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFhirPatientToOmopRunnable implements Runnable {

	private Integer id;

	private Connection conn;

	private String file;

	private String filePath;

	private String filePathShortend;

	public WriteFhirPatientToOmopRunnable(String file, Connection conn, Integer id) {
		this.file = file;
		this.conn = conn;
		this.id = id;
		this.filePath = FileUtil.getCanonicalPath(file);
		this.filePathShortend = filePath;
		if (this.filePath != null && this.filePath.lastIndexOf('/') > 0) {
			this.filePathShortend = filePath.substring((filePath.lastIndexOf('/') + 1), filePath.length());
		}
	}

	@Override
	public void run() {
		try {
			List<String> fileList = FileUtil.listResources(this.file, getClass());
			FhirPatient fhirPatient = new FhirPatientFactory(fileList).buildFhirPatient();
			OmopPerson personEverything = new OmopPersonFactory().build(fhirPatient, conn);
			log.info("DONE: Parsing fhir resource (" + this.id + "): " + this.filePathShortend);
			// write to the database
			WriteOmopPersonToDatabase.exec(personEverything, this.conn);
			log.info("DONE: Writing to database (thread " + this.id + "): " + filePathShortend);
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
