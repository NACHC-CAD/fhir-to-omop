package org.nachc.tools.fhirtoomop.util.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.tools.fhirtoomop.util.params.MySqlAuthParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySqlDatabaseConnectionFactory {

	public static Connection getSyntheaConnection() {
		return getMysqlConnection(MySqlAuthParams.syntheaDb());
	}

	public static Connection getMysqlConnection(String schema) {
		try {
			String url = MySqlAuthParams.getUrl();
			String uid = MySqlAuthParams.getUid();
			String pwd = MySqlAuthParams.getPwd();
			url = url + "/" + schema;
			url = url + "?rewriteBatchedStatements=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			log.info("URL: " + url);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}

	}

}
