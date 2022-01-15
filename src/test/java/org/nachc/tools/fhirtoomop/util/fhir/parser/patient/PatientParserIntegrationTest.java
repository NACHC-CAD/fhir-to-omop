package org.nachc.tools.fhirtoomop.util.fhir.parser.patient;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.extension.ExtensionParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patienteverything.PatientEverythingParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientParserIntegrationTest {

	@Test
	public void shouldParserPatient() {
		log.info("Starting test...");
		String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
		PatientEverythingParser everything = new PatientEverythingParser(json);
		PatientParser patient = everything.getPatient();
		log.info("Got Patient: " + patient);
		// id
		String patientId = patient.getId();
		log.info("PatientId: " + patientId);
		assertTrue(patientId.equals("6f7acde5-db81-4361-82cf-886893a3280c"));
		// extensions
		List<ExtensionParser> extensions = patient.getExtensions();
		log.info("Got " + extensions.size() + " extensions");
		assertTrue(extensions.size() == 7);
		log.info("Done.");
	}

}
