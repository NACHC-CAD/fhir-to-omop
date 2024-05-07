package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabaseUser {

	public static void exec(Connection conn) {
		String db = AppParams.getDbName();
		String uid = AppParams.getUid();
		String pwd = AppParams.getPwd();
		// switch to the using db
		log.info("Using: " + db);
		Database.update("use " + db, conn);
		// create the log in
		log.info("Creating login: " + uid);
		Database.update("create login " + uid + " with password = '" + pwd + "'", conn);
		// create user
		addPrivs(db, uid, conn);
		addPrivs(db + "_dqd_results", uid, conn);
		Database.commit(conn);
		// done
		// do the special grant for bulk upload
		Database.update("use master", conn);
		Database.update("GRANT ADMINISTER BULK OPERATIONS TO " + uid, conn);
		Database.commit(conn);
		// switch back to the current schema
		Database.update("use " + db, conn);
		log.info("Done creating database user.");
	}

	private static void addPrivs(String schemaName, String uid, Connection conn) {
		// switch to the database
		Database.update("use " + schemaName, conn);
		// create the user
		log.info("Creating user: " + uid);
		Database.update("create user " + uid + " for login " + uid + " with default_schema = " + schemaName, conn);
		// add the privs
		log.info("Adding privileges...");
		Database.update("exec sp_addrolemember N'db_accessadmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_backupoperator', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datareader', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datawriter', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_ddladmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_owner', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_securityadmin', N'" + uid + "'", conn);
	}
	
}
