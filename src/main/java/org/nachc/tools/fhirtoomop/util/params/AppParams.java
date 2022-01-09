package org.nachc.tools.fhirtoomop.util.params;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class AppParams {


	public static final Properties PROPS = PropertiesUtil.getAsProperties("app.properties");

	public static String getTestOutputDir() {
		return PROPS.getProperty("testOutputDir");
	}

	public static String OUT_DIR = "/test/synthea-tools";

	public static File getOutDir() {
		return new File(OUT_DIR);
	}
	
	public static File getOutFile(String fileName) {
		return new File(getOutDir(), fileName);
	}
	
}
