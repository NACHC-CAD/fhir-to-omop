package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSystemParserIntegrationTest {

	@Test
	public void shouldGetCodes() {
		log.info("Starting test...");
		File file = FileUtil.getFile("/fhir/terminology/uscore/CodeSystem-cdcrec.json");
		String json = FileUtil.getAsString(file);
		CodeSystemParser parser = new CodeSystemParser(json);
		List<CodeParser> codes = parser.getConcepts();
		log.info("Got " + codes.size() + " codes.");
		log.info("Done.");
	}

}
