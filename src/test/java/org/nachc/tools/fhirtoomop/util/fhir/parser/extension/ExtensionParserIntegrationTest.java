package org.nachc.tools.fhirtoomop.util.fhir.parser.extension;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;
import org.nachc.tools.fhirtoomop.util.fhir.parser.patient.PatientParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtensionParserIntegrationTest {

	@Test
	public void shouldParseExtension() {
		log.info("Starting test...");
		// get the extension to test
		PatientParser patient = TestParams.getPatient();
		List<ExtensionParser> extensions = patient.getExtensions();
		ExtensionParser ex = extensions.get(0);
		// get the url
		String url = ex.getUrl();
		log.info("url: " + url);
		assertTrue(url.equals("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race"));
		// get the code
		String code = ex.getCode();
		log.info("code: " + code);
		assertTrue(code.equals("2106-3"));
		// get the system
		String system = ex.getSystem();
		log.info("system: " + system);
		assertTrue(system.equals("urn:oid:2.16.840.1.113883.6.238"));
		// get the display
		String display = ex.getDisplay();
		log.info("display: " + display);
		assertTrue(display.equals("White"));
		// get as coding
		Coding coding = ex.getCoding();
		assertTrue(coding.getCode().equals(code));
		assertTrue(coding.getSystem().equals(system));
		assertTrue(coding.getDisplay().equals(display));
		log.info("Done.");
	}

}
