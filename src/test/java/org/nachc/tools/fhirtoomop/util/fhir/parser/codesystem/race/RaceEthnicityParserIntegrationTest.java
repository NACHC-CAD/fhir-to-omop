package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeParser;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RaceEthnicityParserIntegrationTest {

	@Test
	public void shouldGetRaceEth() {
		log.info("Starting test...");
		File file = FileUtil.getFile("/fhir/terminology/uscore/CodeSystem-cdcrec.json");
		String json = FileUtil.getAsString(file);
		RaceEthnicityParser parser = new RaceEthnicityParser(json);
		List<CodeParser> races = parser.getRaces();
		log.info("Got race concept");
		log.info("Done.");
	}

}
