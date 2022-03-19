package org.nachc.tools.fhirtoomop.util.synthea.oauth;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.parser.oauth.OAuthTokenResponseParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyntheaOauth {

	public static String fetchToken() {
		String apiKey = AppParams.getSyntheaKeyForToken();
		String secret = AppParams.getSyntheaSecret();
		String url = AppParams.getSyntheaOauthUrl();
		String msg = "{ \"grantType\" : \"client_credentials\", \"scopes\" : \"user/*.read\" }";
		HttpRequestClient http = new HttpRequestClient(url);
		http.addBasicAuthentication(apiKey, secret);
		http.addHeader("Accept", "application/json");
		http.addHeader("Content-Type", "application/json");
		http.doPost(msg);
		String response = http.getResponse();
		log.info("Request: " + http.getUrl());
		log.info("Response: \n" + response);
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
