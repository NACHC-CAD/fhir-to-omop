package org.nachc.tools;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.nachc.tools.fhirtoomop.tools.test.WriteTestPatientsToDatabase;
import org.nachc.tools.fhirtoomop.unittestmanualtest.truncate.TruncateAllDataTablesManualTest;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.counts.GetCountsForAllTablesInSchema;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Row;
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
		log.info("TRUNCATING ALL DATA TABLES IN THE SYNTHEA SCHEMA (PRESERVING CONCEPT TABLES)");
		TruncateAllDataTablesManualTest.main(null);
		log.info("***********************************************************");
		log.info("Done with set up");
		log.info("***********************************************************");
	}

	@AfterClass
	public static void cleanup() {
		log.info("UNCLOSED CONNECTIONS AFTER TESTS: " + OmopDatabaseConnectionFactory.getConnectionCount());
		log.info("TRUNCATING DATA TABLES...");
		TruncateAllDataTablesManualTest.main(null);
		log.info("WRITING PATIENTS TO DATABASE...");
		WriteTestPatientsToDatabase.exec(100);
		String schemaName = AppParams.getFullyQualifiedDbName();
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			Data data = GetCountsForAllTablesInSchema.getCountsForSchema(schemaName, conn);
			log.info("\tcnt\ttable_name");
			for (Row row : data) {
				log.info("\t" + row.get("rowCount") + "\t" + row.get("tableName"));
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("UNCLOSED CONNECTIONS AFTER CLEANUP: " + OmopDatabaseConnectionFactory.getConnectionCount());
		log.info("Done writing patients to database.");
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
		String msg = "";
		msg += "\n\n\n";
		msg += "\n---------------";
		msg += "\nYour instance has been created with the following parameters:  ";
		msg += "\nDatbase:   " + AppParams.getDbName();
		msg += "\nUsername:  " + AppParams.getUid();
		msg += "\nPassword   " + AppParams.getPwd();
		msg += "\nYour welcome :)";
		msg += "\n---------------";
		msg += "\n\n\n";
		log.info(msg);
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
