package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppConnectionParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabaseUser {

	public static void exec(Connection conn) {
		String db = AppConnectionParams.getDbName();
		String uid = AppConnectionParams.getUid();
		String pwd = AppConnectionParams.getPwd();
		// switch to the using db
		log.info("Using: " + db);
		Database.update("use " + db, conn);
		// create the log in
		log.info("Creating login: " + uid);
		Database.update("create login " + uid + " with password = '" + pwd + "'", conn);
		// create the user
		log.info("Creating user: " + uid);
		Database.update("create user " + uid + " for login " + uid + " with default_schema = " + db, conn);
		// create privileges
		log.info("Adding privileges...");
		Database.update("exec sp_addrolemember N'db_accessadmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_backupoperator', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datareader', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_datawriter', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_ddladmin', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_owner', N'" + uid + "'", conn);
		Database.update("exec sp_addrolemember N'db_securityadmin', N'" + uid + "'", conn);
		log.info("Done creating database user.");
	}

}
