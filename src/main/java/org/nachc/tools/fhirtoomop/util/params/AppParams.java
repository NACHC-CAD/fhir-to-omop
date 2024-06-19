package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.nachc.tools.fhirtoomop.util.db.connection.type.ConnectionDbmsType;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParams {

	private static final String SRC = "auth/app.properties";
	
	private static File PARAMS_FILE;

	private static Properties PROPS = null;

	static {
		try {
			// init props
			PROPS = getProps();
			// init props for dev env
			if (PROPS == null) {
				PROPS = getPropsInTestEnv();
			}
			// log if props could not be created (it is set in the code for some applications)
			if (PROPS == null) {
				System.out.println("Could not load default properties.");
				System.out.println("A properties file will need to be provided by the user.");
			}
			String msg = "Properties file:\n\n";
			msg += "-----------------------\n";
			msg += "*\n";
			msg += "* START PROPERTIES FILE\n";
			msg += "*\n";
			msg += "-----------------------\n";
			msg += "\n";
			msg += "--- PROPERTIES FILE NAME -----------------------------\n";
			msg += FileUtil.getCanonicalPath(PARAMS_FILE) + "\n\n"; 
			msg += "--- PROPERTIES FILE CONTENTS -------------------------\n\n";
			msg += FileUtil.getAsString(PARAMS_FILE) + "\n";
			msg += "-----------------------\n";
			msg += "*\n";
			msg += "* END PROPERTIES FILE\n";
			msg += "*\n";
			msg += "-----------------------\n";
			msg += "\n";
			log.debug(msg);
		} catch(Throwable thr) {
			System.out.println("Could not load default properties.");
			System.out.println("A properties file will need to be provided by the user.");
		}
	}

	public static void touch() {
	}
	
	private static Properties getProps() {
		try {
			File srcFile = FileUtil.getFile(SRC, false);
			String fileName = FileUtil.getAsString(srcFile);
			fileName = fileName.trim();
			log.info("AppParams fileName: \n" + fileName);
			// log the working dir
			File workingDir = new File(".");
			log.info("WORKING DIR: " + FileUtil.getCanonicalPath(workingDir));
			// get the parameters file
			File file = new File(fileName);
			if(file.exists() == false) {
				file = FileUtil.getFile(fileName);
			}
			PARAMS_FILE = file;
			log.info("App Properties File Exists: " + file.exists());
			InputStream is = new FileInputStream(file);
			PROPS = PropertiesUtil.getAsProperties(is, fileName);
			return PROPS;
		} catch (Exception exp) {
			log.info("Exception thrown: " + exp.getMessage());
			throw new RuntimeException(exp);
		}
	}

	private static Properties getPropsInTestEnv() {
		try {
			File srcFile = FileUtil.getFile(SRC, true);
			String fileName = FileUtil.getAsString(srcFile);
			File file = new File(fileName);
			PARAMS_FILE = file;
			InputStream is = new FileInputStream(file);
			PROPS = PropertiesUtil.getAsProperties(is, fileName);
			return PROPS;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
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
			log.info("SETTING PROPS FROM: " + fileName);
			File file = new File(fileName);
			InputStream is = new FileInputStream(file);
			PROPS = PropertiesUtil.getAsProperties(is, fileName);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	// ---
	// 
	// METHODS TO GET PROPERTIES
	//
	// ---	

	// ---
	//
	// get property using a key
	//
	// ---

	public static String get(String key) {
		try {
			return PROPS.getProperty(key);
		} catch (Throwable thr) {
			log.info("Could not load property: " + key);
			log.info("PROPS: " + PROPS);
			throw new RuntimeException("Could not load property", thr);
		}
	}

	// ---
	//
	// connection stuff
	//
	// ---

	public static String getBootstrapUrl() {
		return get("bootstrapUrl");
	}

	public static String getUrl() {
		return get("url");
	}

	public static String getUid() {
		return get("uid");
	}

	public static String getPwd() {
		return get("pwd");
	}

	public static String getFullySpecifiedSchemaName() {
		return get("FullySpecifiedSchemaName");
	}

	public static String getFullySpecifiedAchilliesResultsSchemaName() {
		return get("FullySpecifiedAchilliesResultsSchemaName");
	}
	
	public static String getOhdsiDbName() {
		return get("ohdsiDbName");
	}
	
	
	// ---
	//
	// schema names
	//
	// ---
	
	public static String getSchemaName() {
		String rtn = getFullySpecifiedSchemaName();
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

	public static String getVocabSchemaName() {
		String rtn = get("VocabSchemaName");
		return rtn;
	}

	public static String getAchillesTempSchemaName() {
		String rtn = get("atlasTemp");
		return rtn;
	}

	public static String getAchillesResultsSchemaName() {
		String rtn = get("atlasResults");
		return rtn;
	}

	public static String getDqdResultsSchemaName() {
		String rtn = get("DqdResultsSchemaName");
		return rtn;
	}

	// ---
	//
	// postgres stuff
	//
	// ---

	public static String getPostgresBootstrapUrl() {
		return get("postgresBootstrapUrl");
	}

	public static String getPostgresBootstrapUid() {
		return get("postgresBootstrapUid");
	}

	public static String getPostgresBootstrapPwd() {
		return get("postgresBootstrapPwd");
	}

	// terminology stuff

	public static String getTerminologyRootDir() {
		return get("terminologyRootDir");
	}

	// ---
	//
	// upload files stuff
	//
	// ---

	// directory where fhir patients live
	public static String getFhirPatientsDirName() {
		String fileName = get("fhirPatientsDir");
		return fileName;
	}

	// directory where fhir patients live
	public static String getSyntheaPatientsDirName() {
		String fileName = get("syntheaPatientsDir");
		return fileName;
	}

	// max number of connections to use for upload
	public static int getMaxNumberOfConnectionsForUpload() {
		String str = get("maxNumberOfConnectionsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// max number of threads to use for upload
	public static int getMaxNumberOfThreadsForUpload() {
		String str = get("maxNumberOfThreadsForUpload");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	// cache size
	public static int getConceptCacheSize() {
		String str = get("conceptCacheSize");
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
		String str = get("downloadNumberOfPatientsPerThread");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadMaxNumberOfActiveWorkers() {
		String str = get("downloadMaxNumberOfActiveWorkers");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static int getDownloadNumberOfPatientsPerWorker() {
		String str = get("downloadNumberOfPatientsPerWorker");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static String getDownloadOutputDir() {
		return get("downloadOutputDir");
	}

	public static int getDownloadRetryCount() {
		String str = get("downloadRetryCount");
		Integer rtn = StringUtil.parseInt(str);
		return rtn;
	}

	public static String getDownloadPatientIdDir() {
		return get("downloadPatientIdDir");
	}

	public static String getDownloadInputDir() {
		return get("downloadInputDir");
	}

	public static Date getDateNotFound() {
		return TimeUtil.getDateForYyyy_Mm_Dd("1700-01-01");
	}

	public static ConnectionDbmsType getDbmsType() {
		String typeString = get("cdmDbType");
		ConnectionDbmsType rtn = ConnectionDbmsType.get(typeString);
		return rtn;
	}

	// ---
	//
	// webapi parameters
	//
	// ---
	
	public static String getWebApiConnectionString() {
		return get("WebApiConnectionString");
	}

	public static String getWebApiSourceName() {
		return get("WebApiSourceName");
	}

	public static String getWebApiSourceKey() {
		return get("WebApiSourceKey");
	}

	public static String getWebApiSourceJdbcUrl() {
		return get("WebApiSourceJdbcUrl");
	}

	public static String getWebApiSourceDialect() {
		return get("WebApiSourceDialect");
	}

	public static String getSyntheaCsvJdbcLocation() {
		return get("SyntheaCsvJdbcLocation");
	}
	
	public static String getSyntheaCsvJdbcDownloadUrl() {
		return get("SyntheaCsvJdbcDownloadUrl");
	}

	public static boolean getSyntheaCsvDownloadJdbcDriverIfNotFound() {
		String str = get("SyntheaCsvDownloadJdbcDriverIfNotFound");
		if("true".equalsIgnoreCase(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getSyntheaCsvJdbcDriverName() {
		return get("SyntheaCsvJdbcDriverName");
	}

	public static String getSyntheaCsvNativeSchema() {
		return get("SyntheaCsvNativeSchema");
	}
	
	public static String getSyntheaCsvNativeDatabase() {
		return get("SyntheaCsvNativeDatabase");
	}
	
	public static String getServerName() {
		return get("ServerName");
	}
	
	public static String getPort() {
		return get("Port");
	}

	public static String getSyntheaVersion() {
		return get("SyntheaVersion");
	}
	
	public static String getCdmVersion() {
		return get("cdm_version");
	}
	
	public static String getDbmsName() {
		return get("DbmsName");
	}
	
	public static String getSyntehsCsvUid() {
		return get("SyntehsCsvUid");
	}
	
	public static String getSyntehsCsvPwd() {
		return get("SyntheaCsvPwd");
	}
	
	public static String getJdbcExtraSettings() {
		return get("JdbcExtraSettings");
	}
	
	public static String getSyntheaCsvFilesDir() {
		return get("SyntheaCsvFilesDir");
	}
	
	public static String getSyntheaCsvTestFilesUrl() {
		return get("SyntheaCsvTestFilesUrl");
	}
	
	public static boolean getSyntheaCsvDownloadTestFilesIfNotFound() {
		String str = get("SyntheaCsvDownloadTestFilesIfNotFound");
		if("true".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
}
