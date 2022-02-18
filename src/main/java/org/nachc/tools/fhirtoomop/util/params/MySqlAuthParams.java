package org.nachc.tools.fhirtoomop.util.params;

import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class MySqlAuthParams {

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

	public static String syntheaDb() {
		return PROPS.getProperty("syntheaDb");
	}

	public static String syntheaSchema() {
		String rtn = PROPS.getProperty("syntheaDb");
		if(rtn.trim().endsWith(".dbo")) {
			rtn = rtn.trim().substring(0,rtn.trim().indexOf(".dbo"));
		}
		return rtn;
	}

}
