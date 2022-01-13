package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RaceEthnicityParserEthnicityIntegrationTest {

	@Test
	public void shouldGetConcepts() {
		log.info("Starting test...");
		File file = FileUtil.getFile("/fhir/terminology/uscore/CodeSystem-cdcrec.json");
		String json = FileUtil.getAsString(file);
		RaceEthnicityParser parser = new RaceEthnicityParser(json);
		List<CodeParser> races = parser.getEthnicities();
		log.info("Got " + races.size() + " ethnicities.");
		for (CodeParser race : races) {
			log.info("\t" + race.getParentDisplay() + "\t" + race.getCode() + "\t" + race.getDisplay() + "\t" + race.isAbstract());
		}
		log.info("Got " + races.size() + " ethnicities.");
		assertTrue(races.size() > 10);
		log.info("Done.");
	}

}
