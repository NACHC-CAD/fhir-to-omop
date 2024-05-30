package org.nachc.tools.fhirtoomop;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.nachc.tools.fhirtoomop.tools.populate.PopulateOmopInstanceFromFhirFiles;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.counts.GetCountForTable;
import org.nachc.tools.fhirtoomop.util.db.truncatedatatables.TruncateAllDataTables;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.MappedConceptCache;
import org.nachc.tools.fhirtoomop.util.mapping.impl.cache.StandardConceptCache;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.Timer;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WildcardPatternSuite.class)

@SuiteClasses({ "**/*IntegrationTest.class" })
// @SuiteClasses({ "emptytest/EmptyIntegrationTest.class" })
// @SuiteClasses({ "omop/write/threaded/WriteOmopPeopleToDatabaseWorkerIntegrationTest.class" })
// @SuiteClasses({ "omop/write/threaded/**/*IntegrationTest.class" })
// @SuiteClasses({ "omop/**/*.IntegrationTest.class" })
// @SuiteClasses({ "./omop/**/*.IntegrationTest.class" })
// @SuiteClasses({ "omop/**/*.IntegrationTest.class" })
// @SuiteClasses({ "omop/**/**/*.IntegrationTest.class" })

public class RunAllIntegrationTests {

	private static Timer TIMER = new Timer();

	@BeforeClass
	public static void setup() {
		TIMER.start();
		AppParams.touch();
		log.info("***********************************************************");
		log.info("Starting set up");
		log.info("***********************************************************");
		log.info("Truncating tables...");
		TruncateAllDataTables.exec();
		log.info("Done truncating tables.");
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			MappedConceptCache.init(conn);
			StandardConceptCache.init(conn);
		} catch (Throwable thr) {
			throw new RuntimeException(thr);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("***********************************************************");
		log.info("Done with set up");
		log.info("***********************************************************");
	}

	@AfterClass
	public static void cleanup() {
		try {
			log.info("");
			log.info("");
			log.info("Truncating data tables...");
			TruncateAllDataTables.exec();
			log.info("Populating with patients using config file (PopulateOmopInstanceFromFhirFiles)...");
			new PopulateOmopInstanceFromFhirFiles().exec();
			log.info("");
			log.info("");
			log.info("***********************************************************");
			log.info("* * * ");
			log.info("* * * Done with integration tests.");
			log.info("* * *");
			log.info("***********************************************************");
			log.info("");
			TIMER.stop();
			log.info("Start:   " + TIMER.getStartAsString());
			log.info("Stop:    " + TIMER.getStopAsString());
			log.info("Elapsed: " + TIMER.getElapsedString());
			log.info("");
			int patientCount = GetCountForTable.exec("person");
			log.info("There are now " + patientCount + " patients in your OMOP database.");
			int connCount = OmopDatabaseConnectionFactory.getConnectionCount();
			log.info("Open connections after tear down: " + connCount);
			log.info("");
		} finally {
			// FixSequences.exec();
		}
		log.info("Done.");
	}

	public static void exec() {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(RunAllIntegrationTests.class);
		System.out.println("Finished. Result: Failures: " + result.getFailureCount() + ". Ignored: " + result.getIgnoreCount() + ". Tests run: " + result.getRunCount() + ". Time: " + result.getRunTime() + "ms.");
	}

}
