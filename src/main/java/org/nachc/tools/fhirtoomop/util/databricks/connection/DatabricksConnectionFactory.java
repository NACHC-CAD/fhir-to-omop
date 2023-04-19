package org.nachc.tools.fhirtoomop.util.databricks.connection;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;

import com.nach.core.util.databricks.database.DatabricksDbUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabricksConnectionFactory {

	public static Connection getConnection() {
		String url = DatabricksProperties.getJdbcUrl();
		String token = DatabricksProperties.getToken();
		log.info("URL: " + url);
		Connection conn = DatabricksDbUtil.getConnection(url, token);
		DatabricksDbUtil.initParsePolicy(conn);
		log.info("Got connection");
		return conn;
	}

}
