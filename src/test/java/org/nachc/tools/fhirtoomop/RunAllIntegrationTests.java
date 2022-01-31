package org.nachc.tools.fhirtoomop;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.nachc.tools.fhirtoomop.unittestmanualtest.truncate.TruncateAllDataTablesManualTest;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WildcardPatternSuite.class)
@SuiteClasses({ "**/*IntegrationTest.class" })
public class RunAllIntegrationTests {

	@BeforeClass
	public static void setup() {
		log.info("***********************************************************");
		log.info("Starting set up");
		log.info("TRUNCATING ALL DATA TABLES IN THE SYNTHEA SCHEMA (PRESERVING CONCEPT TABLES)");
		TruncateAllDataTablesManualTest.main(null);
		log.info("Done with set up");
		log.info("***********************************************************");
	}

	@AfterClass
	public static void cleanup() {
		log.info("");
		log.info("");
		log.info("***********************************************************");
		log.info("* * * ");
		log.info("* * * Done with integration tests.");
		log.info("* * *");
		log.info("***********************************************************");
		log.info("Done.");
	}

}
