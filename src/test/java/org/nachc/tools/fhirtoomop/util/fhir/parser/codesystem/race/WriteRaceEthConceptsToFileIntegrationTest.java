package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.unittesttool.params.TestParams;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteRaceEthConceptsToFileIntegrationTest {

	@Test
	public void shouldWriteFile() {
		log.info("Starting test...");
		File dir = TestParams.getTestOutputDir();
		File file = FileUtil.getFile("/fhir/terminology/uscore/CodeSystem-cdcrec.json");
		String json = FileUtil.getAsString(file);
		RaceEthnicityParser parser = new RaceEthnicityParser(json);
		parser.writeRaceToFile(new File(dir, "RaceAndEthnicityCDC-Race.csv"));
		parser.writeEthToFile(new File(dir, "RaceAndEthnicityCDC-Eth.csv"));
		parser.writeToFile(new File(dir, "RaceAndEthnicityCDC-Everything.csv"));
		log.info("Done.");
	}

}
