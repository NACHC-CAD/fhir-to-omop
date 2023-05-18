package org.nachc.tools.fhirtoomop.util.databricks.upload;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpFileUpload3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCsvToDatabricks {

	public static boolean exec(String databricksFilePath, File file) {
		// get parameters from properties file
		String baseUrl = DatabricksProperties.getRestUrl();
		String token = DatabricksProperties.getToken();
		// get the parameters to create the database table
		String tableName = file.getParentFile().getName();
		// the base url is the url for the request
		String url = baseUrl;
		// log our parameters
		String msg = "";
		msg += "\n-------------------------";
		msg += "\nFile:        " + FileUtil.getCanonicalPath(file);
		msg += "\nURL:         " + url;
		msg += "\nUpload Path: " + databricksFilePath;
		msg += "\n-------------------------";
		// upload the file
		log.info("Uploading file: " + msg);
		boolean uploadSuccess = HttpFileUpload3.uploadFile(url, token, databricksFilePath, file);
		log.info("success: " + uploadSuccess);
		if (uploadSuccess == false) {
			throw new RuntimeException("Data file upload failed: " + databricksFilePath);
		}
		return uploadSuccess;
	}

}
