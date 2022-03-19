package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteRaceEthConceptsToFileIntegrationTest {

	@Test
	public void shouldWriteFile() {
		log.info("Starting test...");
		File file = FileUtil.getFile("/fhir/terminology/uscore/CodeSystem-cdcrec.json");
		String json = FileUtil.getAsString(file);
		RaceEthnicityParser parser = new RaceEthnicityParser(json);
		parser.writeRaceToFile(AppParams.getTestOutFile("RaceAndEthnicityCDC-Race.csv"));
		parser.writeEthToFile(AppParams.getTestOutFile("RaceAndEthnicityCDC-Eth.csv"));
		parser.writeToFile(AppParams.getTestOutFile("RaceAndEthnicityCDC-Everything.csv"));
		log.info("Done.");
	}

}
