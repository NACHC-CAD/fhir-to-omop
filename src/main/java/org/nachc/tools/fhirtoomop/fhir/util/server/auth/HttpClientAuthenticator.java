package org.nachc.tools.fhirtoomop.fhir.util.server.auth;

import com.nach.core.util.http.HttpRequestClient;

public interface HttpClientAuthenticator {

	public void init();
	
	public void refresh();
	
	public void addAuth(HttpRequestClient client);
	
}
