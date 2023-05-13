package org.nachc.tools.fhirtoomop.util.databricks.database;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
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
	
	public static boolean checkConnection(Connection conn) {
		try {
			Database.query("select 1", conn);
			log.info("Connection is good.");
			return true;
		} catch(Exception exp) {
			log.info("Connection is bad");
			return false;
		}
	}

	public static void close(Connection conn) {
		boolean connectionIsGood = checkConnection(conn);
		if(connectionIsGood) {
			Database.close(conn);
		} else {
			log.info("* * * ");
			log.info("*");
			log.info("* CLOSE CALLED ON BAD CONNECTION");
			log.info("*");
			log.info("* * * ");
			log.info("An attempt to close a connection that was bad was made.");
			log.info("The connection was probably closed (timed out) by the server");
		}
	}
	
	public static Connection resetConnectionIfItIsBad(Connection conn) {
		boolean connectionIsGood = checkConnection(conn);
		if(connectionIsGood == false) {
			log.info("* * * ");
			log.info("*");
			log.info("* BAD CONNECTION DETECTED, RETURNING A NEW CONNECTION");
			log.info("*");
			log.info("* * * ");
			conn = DatabricksConnectionFactory.getConnection();
		}
		return conn;
	}

	
}
