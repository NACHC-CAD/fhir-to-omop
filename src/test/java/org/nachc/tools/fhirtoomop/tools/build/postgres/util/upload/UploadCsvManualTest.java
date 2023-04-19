package org.nachc.tools.fhirtoomop.tools.build.postgres.util.upload;

import java.io.File;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.databricks.DatabricksProperties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpFileUpload3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCsvManualTest {

	@Test
	public void shouldUploadFilesToDatabricks() {
		String baseUrl = DatabricksProperties.getRestUrl();
		String token = DatabricksProperties.getToken();
		String uploadRoot = DatabricksProperties.getDatabricksUploadRoot() + "/demo_cdm";
		String uploadFilePath = uploadRoot + "/concept/concept.csv";
		
		String fileName = "/src/main/resources/databricks/demo_cdm/concept/concept.csv";
		
//		String url = baseUrl + "/dbfs/put";
		String url = baseUrl;
		
		// file to upload
		File file = FileUtil.getFromProjectRoot(fileName);
//		DatabricksFileUtilResponse deleteResponse = DatabricksFileUtilFactory.get().delete("/dbfs/FileStore/tables/test/demo_cdm/concept/concept.csv");
		String msg="";
		msg += "\n-------------------------";
		msg += "\nFile:        " + FileUtil.getCanonicalPath(file);
		msg += "\nURL:         " + url;
		msg += "\nUpload Path: " + uploadFilePath;
		msg += "\n-------------------------";
		log.info("Uploading file: " + msg);
		boolean success = HttpFileUpload3.uploadFile(url, token, uploadFilePath, file);
		log.info("SUCCESS: " + success);

//		boolean success = HttpFileUpload3.uploadFile(this.baseUrl, token, databricksFilePath, file);
	}

}
