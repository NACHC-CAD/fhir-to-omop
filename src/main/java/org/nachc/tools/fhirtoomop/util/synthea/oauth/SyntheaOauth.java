package org.nachc.tools.fhirtoomop.util.synthea.oauth;

import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.parser.oauth.OAuthTokenResponseParser;

public class SyntheaOauth {

	public static String fetchToken() {
		String apiKey = SyntheaParams.getKeyForToken();
		String secret = SyntheaParams.getSecret();
		String url = SyntheaParams.getOauthUrl();
		String msg = "{ \"grantType\" : \"client_credentials\", \"scopes\" : \"user/*.read\" }";
		HttpRequestClient http = new HttpRequestClient(url);
		http.addBasicAuthentication(apiKey, secret);
		http.addHeader("Accept", "application/json");
		http.addHeader("Content-Type", "application/json");
		http.doPost(msg);
		String response = http.getResponse();
		OAuthTokenResponseParser parser = new OAuthTokenResponseParser(response);
		String rtn = parser.getToken();
		return rtn;
	}

	public static void addHeaders(HttpRequestClient http, String token) {
		http.setOauthToken(token);
		http.addHeader("Accept", "application/json");
		http.addHeader("Content-Type", "application/json");
	}
	
}
