package org.nachc.tools.fhirtoomop.fhir.util.server.auth.impl;

import org.nachc.tools.fhirtoomop.fhir.util.server.auth.HttpClientAuthenticator;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.parser.oauth.OAuthTokenResponseParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderTokenAuthenticator implements HttpClientAuthenticator {

	private String token;
	
	@Override
	public void init() {
		this.token = fetchToken();
	}

	@Override
	public void refresh() {
		refresh(0);
	}

	private void refresh(int cnt) {
		try {
			this.token = fetchToken();
		} catch(Exception exp) {
			log.error("! ! ! ERROR REFRESHING TOKEN, TRYING AGAIN ! ! !");
			log.error("! ! !");
			log.error("! ! ! RETRY COUNT=" + cnt);
			log.error("! ! !");
			log.error("! ! ! ERROR REFRESHING TOKEN, TRYING AGAIN ! ! !");
			cnt++;
			refresh(cnt);
		}
	}
	
	@Override
	public void addAuth(HttpRequestClient client) {
		addHeaders(client, token);
	}

	public static String fetchToken() {
		log.info("***********************************");
		log.info("***********************************");
		log.info("***********************************");
		log.info("Getting token...");
		String apiKey = AppParams.get("headerTokenAuthenticatorApiKey");
		String secret = AppParams.get("headerTokenAuthenticatorSecret");
		String url = AppParams.get("headerTokenAuthenticatorUrl");
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
		log.info("Got token: " + rtn);
		log.info("***********************************");
		log.info("***********************************");
		log.info("***********************************");
		return rtn;
	}

	public static void addHeaders(HttpRequestClient client, String token) {
		client.setOauthToken(token);
		client.addHeader("Accept", "application/json");
		client.addHeader("Content-Type", "application/json");
	}

}
