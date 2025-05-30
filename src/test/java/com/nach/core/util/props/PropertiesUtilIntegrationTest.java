package com.nach.core.util.props;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtilIntegrationTest {

	@Test
	public void shouldGetProperties() {
		log.info("Starting test...");
		String fileName = "auth/app.properties";
		File file = FileUtil.getFile(fileName);
		log.info("Got file: " + file);
		log.info("File exists: " + file.exists());
		assertTrue(file.exists());
		log.info("Done.");
	}
	
}
