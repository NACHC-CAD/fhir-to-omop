package org.nachc.tools.fhirtoomop.util.params;

import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class SyntheaParams {

	public static final Properties PROPS = PropertiesUtil.getAsProperties("synthia-auth/auth.properties");

	public static String getOauthUrl() {
		return PROPS.getProperty("oauth-url");
	}

	public static String getUrl() {
		return PROPS.getProperty("url");
	}

	public static String getAppId() {
		return PROPS.getProperty("app-id");
	}

	public static String getKeyForToken() {
		return PROPS.getProperty("key");
	}

	public static String getSecret() {
		return PROPS.getProperty("secret");
	}

}
