package org.nachc.tools.fhirtoomop.tools.download.authenticate;

import java.lang.reflect.Method;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;

public class FhirServerAuthenticator {

	public static void addAuthentication(HttpRequestClient client) {
		try {
			String className = AppParams.get("httpClientAuthenticatorClass");
			Object obj = Class.forName(className).newInstance();
			Class[] sig = { HttpRequestClient.class };
			Object[] args = { client };
			Method method = obj.getClass().getMethod("addAuth", sig);
			method.invoke(obj, args);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
