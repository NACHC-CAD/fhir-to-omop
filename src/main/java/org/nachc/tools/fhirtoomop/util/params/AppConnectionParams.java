package org.nachc.tools.fhirtoomop.util.params;

import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class AppConnectionParams {

	public static final Properties PROPS = PropertiesUtil.getAsProperties("auth/mysql-auth.properties");

	public static String getUrl() {
		return PROPS.getProperty("url");
	}
	
	public static String getUid() {
		return PROPS.getProperty("uid");
	}

	public static String getPwd() {
		return PROPS.getProperty("pwd");
	}

	public static String getSyntheaDb() {
		return PROPS.getProperty("syntheaDb");
	}

	public static String getSyntheaSchema() {
		String rtn = PROPS.getProperty("syntheaDb");
		return rtn;
	}
	
	public static String getCatalogPart(String schemaName) {
		String rtn = schemaName;
		if(rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(0,rtn.trim().indexOf("."));
		}
		return rtn;
	}

	public static String getSchemaPart(String schemaName) {
		String rtn = schemaName;
		if(rtn.indexOf(".") > 0) {
			rtn = rtn.trim().substring(rtn.indexOf(".") + 1, rtn.length());
		}
		return rtn;
	}
	
}