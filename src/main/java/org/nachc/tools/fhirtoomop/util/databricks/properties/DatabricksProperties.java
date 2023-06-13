package org.nachc.tools.fhirtoomop.util.databricks.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabricksProperties {

	//
	// class variables
	//
	
	private static File PROPS_FILE;
	
	private static Properties PROPS = null;
	
	static {
		try {
			InputStream is = FileUtil.getInputStream("/auth/app-databricks.properties");
			boolean exists = false;
			if(is != null) {
				String fileName = FileUtil.getAsString(is);
				if(fileName != null) {
					fileName = fileName.trim();
					PROPS_FILE = new File(fileName);
					exists = PROPS_FILE.exists();
					if(exists == true) {
						PROPS = PropertiesUtil.getAsProperties(PROPS_FILE);
					} else {
						PROPS = null;
					}
				} else {
					PROPS_FILE = null;
					PROPS = null;
				}
			} else {
				PROPS_FILE = null;
				PROPS = null;
			}
			if(exists == true) {
				log.info("File: " + FileUtil.getCanonicalPath(PROPS_FILE));
				log.info("Exists: " + exists);
			} else {
				log.info("------------");
				log.info("File: " + FileUtil.getCanonicalPath(PROPS_FILE));
				log.info("Exists: " + exists);
				log.info("Could not load default properties.");
				log.info("A properties file will need to be provided by the user.");
				log.info("------------");
			}
		} catch(Throwable thr) {
			System.out.println("Could not load default properties.");
			System.out.println("A properties file will need to be provided by the user.");
		}
	}

	/**
	 * 
	 * This method should only be used by the main class to allow the user to set
	 * properties locally
	 * 
	 */
	public static void setProps(InputStream srcIs) {
		try {
			String fileName = FileUtil.getAsString(srcIs);
			fileName = fileName.trim();
			log.info("SETTING PROPS FROM: \n" + fileName);
			PROPS_FILE = new File(fileName);
			InputStream is = new FileInputStream(PROPS_FILE);
			PROPS = PropertiesUtil.getAsProperties(is, fileName);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	//
	// getters for class variables
	//
	
	public static File getPropsFile() {
		return PROPS_FILE;
	}
	
	//
	// get all of the existing keys
	//
	
	public static List<String> getKeys() {
		Set<Object> keySet = PROPS.keySet();
		ArrayList<String> rtn = new ArrayList<String>();
		for(Object obj : keySet) {
			rtn.add(obj + "");
		}
		return rtn;
	}
	
	//
	// get property by name
	//
	
	public static String get(String key) {
		return PROPS.getProperty(key);
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

	public static String getWebApiBootStrapJdbcUrl() {
		return PROPS.getProperty("WebApiBootStrapJdbcUrl");
	}
	
	public static String getWebApiDatabase() {
		return PROPS.getProperty("WebApiDatabase");
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
