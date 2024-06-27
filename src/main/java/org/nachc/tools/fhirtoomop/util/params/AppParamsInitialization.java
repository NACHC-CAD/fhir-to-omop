package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParamsInitialization {

	// ---
	// static variables and initializers
	// ---
	
	protected static final String SRC = "auth/app.properties";
	
	protected static File PARAMS_FILE;

	protected static Properties PROPS = null;

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
	
	// ---
	// method to get the properties 
	// ---

	protected static Properties getProps() {
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

	protected static Properties getPropsInTestEnv() {
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
	 * This method should only be used by the main class to allow the user to set
	 * properties locally
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
	// get property using a key
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

}
