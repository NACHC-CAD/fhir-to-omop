package org.nachc.tools.fhirtoomop.tools.download.authenticate;

import org.nachc.tools.fhirtoomop.fhir.util.server.auth.HttpClientAuthenticator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;

public class FhirServerAuthenticator {

	private static HttpClientAuthenticator AUTH = getNew();
	
	private static Object LOCK = new Object();
	
	private static HttpClientAuthenticator getNew() {
		try {
			String className = AppParams.get("httpClientAuthenticatorClass");
			Object obj = Class.forName(className).newInstance();
			HttpClientAuthenticator rtn = (HttpClientAuthenticator) obj;
			rtn.init();
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public static void auth(HttpRequestClient client) {
		synchronized (LOCK) {
			AUTH.addAuth(client);
		}
	}
	
	public static void refresh() {
		synchronized (LOCK) {
			AUTH.refresh();
		}
	}
	
}
