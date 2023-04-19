package org.nachc.tools.fhirtoomop.util.databricks.upload;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpFileUpload3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadSingleCsvFileToDatabricksManualTest {

	@Test
	public void shouldUploadFilesToDatabricks() {
		String databricksFilePath = "/demo_cdm/concept/concept.csv";
		String fileName = "/src/main/resources/databricks/concept/concept.csv";
		File file = FileUtil.getFromProjectRoot(fileName);
		log.info("Doing delete...");
		DeleteCsvFromDatabricks.exec(databricksFilePath);
		log.info("Doing upload...");
		UploadCsvToDatabricks.exec(databricksFilePath, file);
		log.info("Done.");
	}

}
