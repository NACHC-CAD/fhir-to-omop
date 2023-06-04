package org.nachc.tools.fhirtoomop.util.databricks.properties;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabricksProperties {

	//
	// properties
	//
	
	private static final Properties PROPS;
	
	static {
		InputStream is = FileUtil.getInputStream("/auth/app-databricks.properties");
		String fileName = FileUtil.getAsString(is).trim();
		File file = new File(fileName);
		log.info("File: " + FileUtil.getCanonicalPath(file));
		log.info("Exists: " + file.exists());
		PROPS = PropertiesUtil.getAsProperties(file);
	}

	//
	// REST properties
	//
	
	public static String getRestUrl() {
		return PROPS.getProperty("RestUrl");
	}
	
	public static String getDatabricksUploadRoot() {
		return PROPS.getProperty("DatabricksUploadRoot");
	}
	
	public static String getDatabricksFilesRoot() {
		return PROPS.getProperty("DatabricksFilesRoot");
	}

	//
	// DB properties
	//
	
	public static String getJdbcUrl() {
		return PROPS.getProperty("JdbcUrl");
	}
	
	public static String getToken() {
		return PROPS.getProperty("Token");
	}

	//
	// Schema properties
	//
	
	public static String getSchemaName() {
		return PROPS.getProperty("SchemaName");
	}
	
	public static String getVocabSchemaName() {
		return PROPS.getProperty("VocabSchemaName");
	}
	
	public static String getAchillesTempSchemaName() {
		return PROPS.getProperty("AchillesTempSchemaName");
	}
	
	public static String getAchillesResultsSchemaName() {
		return PROPS.getProperty("AchillesResultsSchemaName");
	}
	
	//
	// WebApi properties
	//
	
	public static String getWebApiJdbcUrl() {
		return PROPS.getProperty("WebApiJdbcUrl");
	}

	public static String getWebApiSchema() {
		return PROPS.getProperty("WebApiSchema");
	}
	
	public static String getWebApiKey() {
		return PROPS.getProperty("WebApiKey");
	}

	public static String getWebApiName() {
		return PROPS.getProperty("WebApiName");
	}
	
}
