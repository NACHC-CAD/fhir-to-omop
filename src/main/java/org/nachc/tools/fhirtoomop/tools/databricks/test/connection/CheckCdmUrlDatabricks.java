package org.nachc.tools.fhirtoomop.tools.databricks.test.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckCdmUrlDatabricks {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
		Connection conn = null;
		try {
			// get the url
			String url = DatabricksProperties.getJdbcUrl();
			String token = DatabricksProperties.getToken();
			url = url + token;
			log.info("URL: \n" + url);
			// get the connection
			log.info("Getting connection...");
			conn = DriverManager.getConnection(url);
			log.info("Got connection, doing select");
			Data data = Database.query("select 1 as cnt", conn);
			log.info("Got data...");
			String cnt = data.get(0).get("cnt");
			log.info("cnt: " + cnt);
			log.info("Done with check.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
	}
	
}
