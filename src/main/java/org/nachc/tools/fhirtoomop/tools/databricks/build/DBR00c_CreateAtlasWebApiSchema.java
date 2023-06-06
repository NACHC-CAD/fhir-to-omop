package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/* * * 
 * 
 * This class drops the webapi schema if it exists.  
 * It then creates the schema and all required privileges.  
 * This class does not create the tables etc. (that's done in the next step).  
 * 
 * * */

@Slf4j
public class DBR00c_CreateAtlasWebApiSchema {

	private static final String FILE_PATH = "/databricks/webapi/A09_CreateAtlasWebApiSchema.sql";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DatabricksWebApiConnectionFactory.getConnection();
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		log.info("Creating Atlas webapi schema.");
		log.info("getting sql script...");
		log.info("executing script...");
		String sqlString = getSqlString();
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating Atlas webapi schema.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiAdminUid>", DatabricksProperties.get("OhdsiAdminUid"));
		sqlString = sqlString.replace("<ohdsiAdminUserUid>", DatabricksProperties.get("OhdsiAdminUserUid"));
		sqlString = sqlString.replace("<ohdsiAppUid>", DatabricksProperties.get("OhdsiAppUid"));
		sqlString = sqlString.replace("<webApiSchema>", DatabricksProperties.getWebApiSchema());
		return sqlString;
	}

}
