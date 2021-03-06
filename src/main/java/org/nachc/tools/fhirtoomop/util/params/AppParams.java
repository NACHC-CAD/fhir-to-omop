package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.yaorma.util.time.TimeUtil;

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

	// ---
	//
	// connection stuff
	//
	// ---

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

	// ---
	//
	// postgres stuff
	//
	// ---
	
	public static String getPostgresBootstrapUrl() {
		return PROPS.getProperty("postgresBootstrapUrl");
	}
	
	public static String getPostgresBootstrapUid() {
		return PROPS.getProperty("postgresBootstrapUid");
	}
	
	public static String getPostgresBootstrapPwd() {
		return PROPS.getProperty("postgresBootstrapPwd");
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

	// directory where fhir patients live
	public static String getSyntheaPatientsDirName() {
		String fileName = PROPS.getProperty("syntheaPatientsDir");
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

	public static int getMaxNumberOfWorkersForUpload() {
		String str = AppParams.get("maxNumberOfWorkersForUpload");
		Integer rtn = Integer.parseInt(str);
		return rtn;
	}

	// ---
	//
	// Download files stuff
	//
	// ---

	public static File getFhirPatientIdDir() {
		String dirName = AppParams.getDownloadPatientIdDir();
		File file = new File(dirName);
		return file;
	}
	
	public static String getFhirPatientServerUrl() {
		return AppParams.get("fhirPatientServerUrl");
	}

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

	public static String getDownloadOutputDir() {
		return PROPS.getProperty("downloadOutputDir");
	}

	public static int getDownloadRetryCount() {
		String str = PROPS.getProperty("downloadRetryCount");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static String getDownloadPatientIdDir() {
		return PROPS.getProperty("downloadPatientIdDir");
	}

	public static String getDownloadInputDir() {
		return PROPS.getProperty("downloadInputDir");
	}

	public static Date getDateNotFound() {
		return TimeUtil.getDateForYyyy_Mm_Dd("1700-01-01");
	}
	
}
