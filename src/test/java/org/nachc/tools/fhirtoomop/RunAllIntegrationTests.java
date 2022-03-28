package org.nachc.tools.fhirtoomop;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.yaorma.util.time.Timer;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WildcardPatternSuite.class)
@SuiteClasses({ "**/*IntegrationTest.class" })
public class RunAllIntegrationTests {

	private static Timer TIMER = new Timer();

	@BeforeClass
	public static void setup() {
		TIMER.start();
		log.info("***********************************************************");
		log.info("Starting set up");
		log.info("***********************************************************");
		log.info("***********************************************************");
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
		log.info("");
		log.info("");
		TIMER.stop();
		log.info("Start:   " + TIMER.getStartAsString());
		log.info("Stop:    " + TIMER.getStopAsString());
		log.info("Elapsed: " + TIMER.getElapsedString());
		log.info("Done.");
	}

	public static void exec() {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(RunAllIntegrationTests.class);
		System.out.println("Finished. Result: Failures: " +
				result.getFailureCount() + ". Ignored: " +
				result.getIgnoreCount() + ". Tests run: " +
				result.getRunCount() + ". Time: " +
				result.getRunTime() + "ms.");
	}

}
