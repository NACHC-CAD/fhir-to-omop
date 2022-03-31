package org.nachc.tools.fhirtoomop.util.params;

import java.io.InputStream;
import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParams {

	private static final String DEFAULT = "auth/app.properties";

	private static Properties PROPS = null;

	static {
		try {
			PROPS = PropertiesUtil.getAsProperties(DEFAULT);
		} catch (Exception exp) {
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
	public static void setProps(InputStream is) {
		PROPS = PropertiesUtil.getAsProperties(is, "User's app.properties");
	}

	public static void resetToDefault() {
		PROPS = PropertiesUtil.getAsProperties(DEFAULT);
	}

	// passthrough method
	public static String get(String key) {
		return PROPS.getProperty(key);
	}

	// connection stuff

	public static String getBootstrapUrl() {
		return PROPS.getProperty("bootstrapUrl");
	}

	public static String getUrl() {
		return PROPS.getProperty("url");
	}

	public static String getUid() {
		return PROPS.getProperty("uid");
	}

	public static String getPwd() {
		return PROPS.getProperty("pwd");
	}

	public static String getFullyQualifiedDbName() {
		return PROPS.getProperty("syntheaDb");
	}

	public static String getDbName() {
		String rtn = PROPS.getProperty("syntheaDb");
		return getCatalogPart(rtn);
	}

	public static String getCatalogPart(String schemaName) {
		String rtn = schemaName;
		if (rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(0, rtn.trim().indexOf("."));
		}
		return rtn;
	}

	public static String getSchemaPart(String schemaName) {
		String rtn = schemaName;
		if (rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(rtn.indexOf(".") + 1, rtn.length());
		}
		return rtn;
	}

	// terminology stuff

	public static String getTerminologyRootDir() {
		return PROPS.getProperty("terminologyRootDir");
	}

	// ---
	//
	// upload files stuff
	//
	// ---

	// directory where fhir patients live
	public static String getFhirPatientsDirName() {
		String fileName = PROPS.getProperty("fhirPatientsDir");
		return fileName;
	}

	// max number of connections to use for upload
	public static int getMaxNumberOfConnectionsForUpload() {
		String str = PROPS.getProperty("maxNumberOfConnectionsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// max number of threads to use for upload
	public static int getMaxNumberOfThreadsForUpload() {
		String str = PROPS.getProperty("maxNumberOfThreadsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// cache size
	public static int getConceptCacheSize() {
		String str = PROPS.getProperty("conceptCacheSize");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// ---
	//
	// Download files stuff
	//
	// ---

	public static int getDownloadNumberOfPatientsPerThread() {
		String str = PROPS.getProperty("downloadNumberOfPatientsPerThread");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadMaxNumberOfActiveWorkers() {
		String str = PROPS.getProperty("downloadMaxNumberOfActiveWorkers");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadNumberOfPatientsPerWorker() {
		String str = PROPS.getProperty("downloadNumberOfPatientsPerWorker");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	/*
	 * 
	 * // local files stuff
	 * 
	 * public static File getFhirPatientIdDir() { String dirName =
	 * PROPS.getProperty("fhirPatientIdDir"); return new File(dirName); }
	 * 
	 * public static List<String> getFhirPatientsDirListing() { String fileName =
	 * getFhirPatientsDirName(); log.info("+++++++++++++++++++++++++++++");
	 * log.info("Getting listing for: " + fileName);
	 * log.info("+++++++++++++++++++++++++++++"); List<String> rtn =
	 * FileUtil.listResources(fileName, AppParams.class); return rtn; }
	 * 
	 * // // create a test output file //
	 * 
	 * public static File getTestOutFile(String fileName) { String dirName =
	 * PROPS.getProperty("testOutputDir"); return new File(dirName, fileName); }
	 * 
	 * // synthea stuff
	 * 
	 * public static String getSyntheaOauthUrl() { return
	 * PROPS.getProperty("fhir-server-oauth-url"); }
	 * 
	 * public static String getSyntheaUrl() { return
	 * PROPS.getProperty("fhir-server-url"); }
	 * 
	 * public static String getSyntheaAppId() { return
	 * PROPS.getProperty("synthea-app-id"); }
	 * 
	 * public static String getSyntheaKeyForToken() { return
	 * PROPS.getProperty("synthea-key"); }
	 * 
	 * public static String getSyntheaSecret() { return
	 * PROPS.getProperty("synthea-secret"); }
	 * 
	 * // umls stuff
	 * 
	 * public static String getUmlsApiKey() { return
	 * PROPS.getProperty("umls-api-key"); }
	 * 
	 * // dirs for a production run
	 * 
	 * public static File getPatientIdsDir_PROD() { String dirName =
	 * PROPS.getProperty("fhirPatientIdDir_PROD"); File file = new File(dirName);
	 * return file; }
	 * 
	 * public static File getPatientDir_PROD() { String dirName =
	 * PROPS.getProperty("fhirPatientsDir_PROD"); File file = new File(dirName);
	 * return file; }
	 */

}
