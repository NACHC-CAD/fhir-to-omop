package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.batch.operations.BatchRuntimeException;

import org.nachc.tools.fhirtoomop.util.db.connection.type.ConnectionDbmsType;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParams {

	private static final String SRC = "auth/app.properties";

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
		} catch(Throwable thr) {
			System.out.println("Could not load default properties.");
			System.out.println("A properties file will need to be provided by the user.");
		}
	}

	private static Properties getProps() {
		try {
			File srcFile = FileUtil.getFile(SRC, false);
			String fileName = FileUtil.getAsString(srcFile);
			fileName = fileName.trim();
			log.info(fileName);
			//			fileName = "C:\\_WORKSPACES\\nachc\\_CURRENT\\KEYS\\application-auth\\fhir-to-omop\\app.properties";
			log.info(fileName);
			File file = new File(fileName);
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
			throw new BatchRuntimeException("Could not load property", thr);
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

	public static String getFullyQualifiedDbName() {
		return get("syntheaDb");
	}

	public static String getDbName() {
		String rtn = get("syntheaDb");
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

}
