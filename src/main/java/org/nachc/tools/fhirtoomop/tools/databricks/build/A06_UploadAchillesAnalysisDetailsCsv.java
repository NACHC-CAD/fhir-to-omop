package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.InputStream;

import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.databricks.file.DatabricksFileUtil;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A06_UploadAchillesAnalysisDetailsCsv {

	private static final String FILE_NAME = "achilles_analysis_details.csv";
	
	private static final String FILE_PATH = "/databricks/achilles/" + FILE_NAME;

	public static void main(String[] args) {
		String databricksFilesRoot = DatabricksProperties.getDatabricksFilesRoot();
		exec(databricksFilesRoot);
		log.info("Done.");
	}
	
	public static void exec(String databricksFilesRoot) {
		log.info("Uplaoding achilles_analysis_details.csv");
		String uploadRoot = DatabricksProperties.getDatabricksUploadRoot();
		databricksFilesRoot = uploadRoot + "/" + databricksFilesRoot + "/etc/achilles";
		log.info("Dir for achilles file: \n" + databricksFilesRoot);
		// delete existing files
		log.info("Deleting existing files...");
		DeleteCsvFromDatabricks.exec(databricksFilesRoot, true);
		log.info("Databricks file dir: " + databricksFilesRoot);
		InputStream is = FileUtil.getInputStream(FILE_PATH);
		log.info("Got input stream: " + is);
		uploadFile(is, databricksFilesRoot, FILE_NAME);
		log.info("Done uploading file.");
	}
	
	private static void uploadFile(InputStream is, String databricksFilesRoot, String fileName) {
		log.info("Doing upload...");
		String url = DatabricksProperties.getRestUrl();
		String token = DatabricksProperties.getToken();
		String databricksFilePath = databricksFilesRoot + "/" + fileName;
		log.info("Uploading file to: " + databricksFilePath);
		new DatabricksFileUtil(url, token).put(databricksFilesRoot, is, fileName, true);
		log.info("Done with upload");
	}


}
