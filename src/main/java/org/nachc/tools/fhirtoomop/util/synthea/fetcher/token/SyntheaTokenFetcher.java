package org.nachc.tools.fhirtoomop.util.synthea.fetcher.token;

import org.nachc.tools.fhirtoomop.util.params.SyntheaParams;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.parser.oauth.OAuthTokenResponseParser;

public class SyntheaTokenFetcher {

	public static String fetchToken() {
		String apiKey = SyntheaParams.getKey();
		String secret = SyntheaParams.getSecret();
		String url = SyntheaParams.getOauthUrl();
		String msg =  "{ \"grantType\" : \"client_credentials\", \"scopes\" : \"user/*.read\" }";
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

}
