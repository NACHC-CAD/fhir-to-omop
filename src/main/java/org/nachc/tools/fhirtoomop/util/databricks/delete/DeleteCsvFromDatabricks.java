package org.nachc.tools.fhirtoomop.util.databricks.delete;

import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.databricks.file.response.DatabricksFileUtilResponse;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteCsvFromDatabricks {

	public static DatabricksFileUtilResponse exec(String filePath) {
		return exec(filePath, false);
	}

	
	public static DatabricksFileUtilResponse exec(String filePath, Boolean recursive) {
		if(filePath == null || filePath.trim().length() == 0) {
			throw new RuntimeException("Invalid filePath: " + filePath);
		}
		if(filePath.trim().equals("/FileStore/tables/prod") || filePath.trim().equals("/FileStore/tables/prod/")) {
			throw new RuntimeException("Please don't delete all of production.");
		}
		// get params from properties file
		String baseUrl = DatabricksProperties.getRestUrl();
		String token = DatabricksProperties.getToken();
		String uploadRoot = DatabricksProperties.getDatabricksUploadRoot();
		// update the file path to make it fully qualified
		if(uploadRoot.endsWith("/")) {
			filePath = uploadRoot + filePath;
		} else {
			filePath = uploadRoot + "/" + filePath;
		}
		// create the delete url
		String url = baseUrl + "/dbfs/delete";
		// create the client and send the request
		HttpRequestClient client = new HttpRequestClient(url);
		client.setOauthToken(token);
		String json = null;
		if(recursive == true) {
			json = "{\"path\":\"" + filePath + "\"}";
		} else {
			json = "{\"path\":\"" + filePath + "\",\"recursive\":\"true\"}";
		}
		log.info("Deleting file from Databricks: " + filePath);
		log.info("url: " + client.getUrl());
		log.info("json: \n" + json);
		client.doPost(json);
		// create and return the response
		DatabricksFileUtilResponse rtn = new DatabricksFileUtilResponse();
		rtn.init(client);
		log.info("got response: \n" + rtn.getResponse());
		log.info("Response code: " + rtn.getStatusCode());
		return rtn;
	}

}
