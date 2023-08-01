package org.nachc.tools.fhirtoomop.tools.databricks.test.properties;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDatabricksProperties {

	private static final String POINTER_FILE_NAME = "./auth/app-databricks.properties";
	
	//
	// instance variables
	//
	
	private File configFile;

	private String configFileName;

	private List<String> errorMessages = new ArrayList<String>();
	
	private boolean configFileExists = false;

	//
	// getters
	//
	
	public List<String> getErrorMessages() {
		return this.errorMessages;
	}
	
	public boolean isValid() {
		return errorMessages.size() == 0;
	}

	public boolean configFileExists() {
		return this.configFileExists;
	}
	
	//
	// constructor
	//
	
	public TestDatabricksProperties() {
		checkPointerFile();
		this.configFile = DatabricksProperties.getPropsFile();
		this.configFileName = FileUtil.getCanonicalPath(configFile);
		if(configFileName == null) {
			errorMessages.add("Could not get config file: You need to add this file to your java classpath: " + POINTER_FILE_NAME);
		}
		if(configFile == null) {
			errorMessages.add("Could not file file: " + configFileName);
		} 
		if(configFile != null && configFile.exists() == false) {
			errorMessages.add("File does not exist: " + FileUtil.getCanonicalPath(configFile));
		}
		if(configFile != null && configFile.exists() == true) {
			this.configFileExists = true;
		}
		String msg = "\n\n";
		msg += "---------\n";
		msg += "Databricks Configuration: \n";
		msg += "Config File Name:   " + configFileName + "\n";
		msg += "Config File Path:   " + FileUtil.getCanonicalPath(configFile) + "\n";
		msg += "Config File Exists: " + this.configFileExists() + "\n";
		msg += "---------\n\n";
		log.info(msg);
		showConfigFile();
		showConfigValues();
	}

	private void checkPointerFile() {
		try  {
			String configFileName = FileUtil.getAsString(POINTER_FILE_NAME);
			if(configFileName == null) {
				throw new RuntimeException("file not found");
			}
			configFileName = configFileName.trim();
			log.info("Found pointer file: " + POINTER_FILE_NAME);
			log.info("Pointer file points to config file: \n" + configFileName);
		} catch(Exception exp) {
			String msg = "Could not find init file: " + POINTER_FILE_NAME;
			log.error(msg);
			this.errorMessages.add(msg);
		}
	}
	
	private void showConfigFile() {
		String str = FileUtil.getAsString(this.configFile);
		String msg = "\n\n";
		msg += "---------------------------\n";
		msg += "START CONFIG FILE CONTENTS\n";
		msg += "---------------------------\n";
		msg += "\n";
		msg += str;
		msg += "\n";
		msg += "---------------------------\n";
		msg += "END CONFIG FILE CONTENTS\n";
		msg += "---------------------------\n";
		log.info(msg);
	}

	private void showConfigValues() {
		String msg = "\n\n";
		msg += "---------------------------\n";
		msg += "START CONFIG VALUES\n";
		msg += "---------------------------\n";
		List<String> keys = DatabricksProperties.getKeys();
		Collections.sort(keys);
		for(String key : keys) {
			String val = DatabricksProperties.get(key + "");
			msg += StringUtils.rightPad(key + "",30) + val + "\n";
		}
		msg += "---------------------------\n";
		msg += "END CONFIG VALUES\n";
		msg += "---------------------------\n";
		msg += "\n";
		log.info("Config parameters: " + msg);
	}
}
