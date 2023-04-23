package org.nachc.tools.fhirtoomop.util.databricks.database;

import java.sql.Connection;

import org.yaorma.database.Database;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabricksDatabase {

	private static final int RETRIES = 5;
	
	public static void update(String sqlString, Connection conn) {
		update(sqlString, conn, RETRIES);
	}
	
	private static void update(String sqlString, Connection conn, int retries) {
		try {
			retries--;
			Database.update(sqlString, conn);
		} catch(Exception exp) {
			if(retries > 0) {
				log.info("* * *");
				log.info("*");
				log.info("* ERROR WITH UPDATE TO DATA BRICKS, RETRYING ");
				log.info("*");
				log.info("* * *");
				TimeUtil.sleep(1);
				update(sqlString, conn, retries);
			} else {
				log.info("Done with retrys, throwing exception...");
				throw(new RuntimeException(exp));
			}
		}
	}
	
}
