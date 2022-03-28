package org.nachc.tools.fhirtoomop.fhir.parser.patient;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.fhir.parser.bundle.BundleParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientParserIntegrationTest {

	private static final String FILE_PATH = "/test/fhir/test-patient-01/5acc8bb4-2d14-4461-a560-228d96459cc3/0_5acc8bb4-2d14-4461-a560-228d96459cc3_8a37ef8f-098c-438a-a50c-8210f1ce5a4f.json";

	@Test
	public void shouldGetPatient() {
		log.info("Starting test...");
		String json = FileUtil.getAsString(FILE_PATH);
		BundleParser bundleParser = new BundleParser(json);
		Patient patient = bundleParser.getResourceForType(Patient.class);
		PatientParser pat = new PatientParser(patient);
		// id
		String id = pat.getId();
		log.info("ID: " + id);
		assertTrue(id.equals("5acc8bb4-2d14-4461-a560-228d96459cc3"));
		// bday
		Date bday = pat.getBirthDate();
		log.info("bday: " + bday);
		assertTrue(bday != null);
		// race
		String race = pat.getRaceStr();
		log.info("race: " + race);
		assertTrue(race.startsWith("2028-9"));
		// eth
		String eth = pat.getEthnicityStr();
		assertTrue(eth.startsWith("2186-5"));
		log.info("eth " + eth);
		log.info("Done.");
	}

}
