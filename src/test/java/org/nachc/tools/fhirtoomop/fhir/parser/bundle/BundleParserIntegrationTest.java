package org.nachc.tools.fhirtoomop.fhir.parser.bundle;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BundleParserIntegrationTest {

	private static final String FILE_PATH = "/fhir/bundle/0_5acc8bb4-2d14-4461-a560-228d96459cc3_8a37ef8f-098c-438a-a50c-8210f1ce5a4f.json";
	
	private static final String NEXT_URL = "https://syntheticmass.mitre.org/v1/fhir/Patient/5acc8bb4-2d14-4461-a560-228d96459cc3/$everything?_count=100&page_token=CoIBcHJvamVjdHMvc3ludGhlYS9sb2NhdGlvbnMvdXMtY2VudHJhbDEvZGF0YXNldHMvMjAxOTAyL2ZoaXJTdG9yZXMvc3ludGhldGljLW1hc3MvZmhpci9QYXRpZW50LzVhY2M4YmI0LTJkMTQtNDQ2MS1hNTYwLTIyOGQ5NjQ1OWNjMyBk";
	
	@Test
	public void shouldParseBundle() {
		log.info("Starting test...");
		String json = FileUtil.getAsString(FILE_PATH);
		BundleParser parser = new BundleParser(json);
		assertTrue(parser.getJson() != null);
		assertTrue(parser.getBundle() != null);
		List<String> resourceTypes = parser.getResourceTypes();
		log.info("Got " + resourceTypes.size() + " resource types.");
		assertTrue(resourceTypes.size() == 100);
		Patient patient = parser.getResourceForType(Patient.class);
		assertTrue(patient != null);
		List<Encounter> encounterList = parser.getResourceListForType(Encounter.class);
		log.info("Got " + encounterList.size() + " encounters");
		assertTrue(encounterList.size() == 19);
		String nextUrl = parser.getNextUrl();
		log.info("Got next url: " + nextUrl);
		assertTrue(NEXT_URL.equals(nextUrl));
		log.info("Done.");
	}
	
}
