package org.nachc.tools.fhirtoomop.util.fhirtoomop.patient;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.omop.yaorma.dvo.PersonDvo;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FhirToOmopPersonParserIntegrationTest {

	@Test
	public void shouldGetOmopPerson() {
		log.info("Starting test...");
		String json = FileUtil.getAsString("/fhir/patient/everything/everything-patient.json");
		PersonDvo person = FhirToOmopPersonParser.getPerson(json);
		int id = person.getPersonId();
		log.info("Got id: " + id);
		assertTrue(id > 0);
		log.info("Done.");
	}
	
}
