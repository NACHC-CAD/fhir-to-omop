package org.nachc.tools.fhirtoomop.tools.databricks.testconnection;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDatabricksConnection {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = DatabricksConnectionFactory.getConnection();
			log.info("Doing query...");
			Data data = Database.query("select 1 as test", conn);
			log.info("Got value from database: " + data.get(0).get("test"));
			log.info("Done testing database.");
		} finally {
			Database.close(conn);
		}
	}
}
