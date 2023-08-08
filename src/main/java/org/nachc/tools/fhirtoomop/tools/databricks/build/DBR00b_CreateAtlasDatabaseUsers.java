package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.connection.webapi.DatabricksWebApiConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates the database users for the PostgreSQL database used by Atlas/WebAPI.  
 *
 */

@Slf4j
public class DBR00b_CreateAtlasDatabaseUsers {

	private static final String FILE_PATH = "/databricks/webapi/A08_CreateAtlasDatabaseUsers.sql";

	public static void main(String[] args) {
		Connection conn = DatabricksWebApiConnectionFactory.getConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(Connection conn) {
		log.info("getting sql script...");
		log.info("executing script...");
		String uid = DatabricksProperties.get("OhdsiAdminUserUid");
		boolean userExists = userExists(uid, conn);
		if(userExists == false) {
			log.info(uid + " user not found, creating users...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
			log.info("Done creating users.");
		} else {
			log.info("USERS EXIST, SKIPPING CREATE USERS");
		}
		log.info("Done with init postgres users for Atlas.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = replace(sqlString, "<ohdsiAdminUserUid>", DatabricksProperties.get("OhdsiAdminUserUid"));
		sqlString = replace(sqlString, "<ohdsiAdminUserPwd>", DatabricksProperties.get("OhdsiAdminUserPwd"));
		sqlString = replace(sqlString, "<ohdsiAdminUid>", DatabricksProperties.get("OhdsiAdminUid"));
		sqlString = replace(sqlString, "<ohdsiAdminPwd>", DatabricksProperties.get("OhdsiAdminPwd"));
		sqlString = replace(sqlString, "<ohdsiAppUserUid>", DatabricksProperties.get("OhdsiAppUserUid"));
		sqlString = replace(sqlString, "<ohdsiAppUserPwd>", DatabricksProperties.get("OhdsiAppUserPwd"));
		sqlString = replace(sqlString, "<ohdsiAppUid>", DatabricksProperties.get("OhdsiAppUid"));
		sqlString = replace(sqlString, "<ohdsiAppPwd>", DatabricksProperties.get("OhdsiAppPwd"));
		return sqlString;
	}

	private static String replace(String sqlString, String src, String dst) {
		return sqlString.replace(src, dst);
	}

	private static boolean userExists(String uid, Connection conn) {
		String sqlString = "";
		sqlString += "SELECT * from ( \n";
		sqlString += "SELECT  \n";
		sqlString += "	usename AS username, \n";
		sqlString += "  CASE  \n";
		sqlString += "     WHEN usesuper AND usecreatedb THEN  \n";
		sqlString += "	   CAST('superuser, create database' AS pg_catalog.text) \n";
		sqlString += "     WHEN usesuper THEN  \n";
		sqlString += "	    CAST('superuser' AS pg_catalog.text) \n";
		sqlString += "     WHEN usecreatedb THEN  \n";
		sqlString += "	    CAST('create database' AS pg_catalog.text) \n";
		sqlString += "     ELSE  \n";
		sqlString += "	    CAST('' AS pg_catalog.text) \n";
		sqlString += "  END role_list \n";
		sqlString += "FROM pg_catalog.pg_user \n";
		sqlString += ") users \n";
		sqlString += "WHERE username = ? \n";
		Data data = Database.query(sqlString,  uid, conn);
		if(data.size() == 1) {
			String fromDb = data.get(0).get("username");
			if(uid != null && uid.equals(fromDb)) {
				return true;
			}
		}
		return false;
	}
	
}
