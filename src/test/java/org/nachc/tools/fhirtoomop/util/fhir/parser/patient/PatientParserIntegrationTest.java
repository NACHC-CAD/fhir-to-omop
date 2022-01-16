package org.nachc.tools.fhirtoomop.util.fhir.parser.patient;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
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
		// get extension for url
		ExtensionParser raceExtension = patient.getExtension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
		log.info("Got extension for race: " + raceExtension.getDisplay());
		// get race
		Coding race = patient.getRace();
		log.info("race code:    " + race.getCode());
		log.info("race system:  " + race.getSystem());
		log.info("race display: " + race.getDisplay());
		assertTrue("2106-3".equals(race.getCode()));
		assertTrue("urn:oid:2.16.840.1.113883.6.238".equals(race.getSystem()));
		assertTrue("White".equals(race.getDisplay()));
		// get ethnicity
		Coding eth = patient.getEthnicity();
		log.info("eth code:    " + eth.getCode());
		log.info("eth system:  " + eth.getSystem());
		log.info("eth display: " + eth.getDisplay());
		assertTrue("2186-5".equals(eth.getCode()));
		assertTrue("urn:oid:2.16.840.1.113883.6.238".equals(eth.getSystem()));
		assertTrue("Not Hispanic or Latino".equals(eth.getDisplay()));
		// gender
		String gender = patient.getGender().toCode();
		log.info("gender: " + gender);
		assertTrue(gender.equals("male"));
		// birth date
		String birthDateString = patient.getBirthDateAsString();
		Integer birthYear = patient.getBirthYear();
		log.info("Got birth date: " + birthDateString);
		assertTrue(birthDateString.equals("1991-02-10"));
		log.info("Got birth year: " + birthYear);
		assertTrue(birthYear == 1991);
		// done
		log.info("Done.");
	}

}
