package org.nachc.tools.fhirtoomop.util.params;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppParamsIntegrationTest {

	File file = FileUtil.getFile("/main/example-params/app.properties");

	@Test
	public void shouldBeDummyKeys() {
		log.info("Starting test...");
		try {
			InputStream is = new FileInputStream(file);
			AppParams.setProps(is);
			String umlsApiKey = AppParams.getUmlsApiKey();
			String syntheaAppId = AppParams.getSyntheaAppId();
			String syntheaKey = AppParams.getSyntheaKeyForToken();
			String syntheaSecret = AppParams.getSyntheaSecret();
			log.info("umlsApiKey:    " + umlsApiKey);
			log.info("syntheaAppId:  " + syntheaAppId);
			log.info("syntheaKey:    " + syntheaKey);
			log.info("syntheaSecret: " + syntheaSecret);
			assertTrue(umlsApiKey.indexOf("xxxxxxxx") == 0);
			assertTrue(syntheaAppId.indexOf("xxxxxxxx") == 0);
			assertTrue(syntheaKey.indexOf("xxxxxxxx") == 0);
			assertTrue(syntheaSecret.indexOf("xxxxxxxx") == 0);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			AppParams.resetToDefault();
		}
		log.info("Done.");
	}

}
