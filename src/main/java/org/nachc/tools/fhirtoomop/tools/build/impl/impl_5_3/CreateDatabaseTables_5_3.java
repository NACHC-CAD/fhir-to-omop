package org.nachc.tools.fhirtoomop.tools.build.impl.impl_5_3;

import java.io.InputStream;
import java.sql.Connection;

import org.hsqldb.lib.StringInputStream;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabaseTables_5_3 {

	private static final String FILE_NAME = "/sqlserver/omop/5.3/OMOPCDM_sql_server_5.3_ddl.sql";
	
	public static void main(String[] args) {
		Connection conn = OmopDatabaseConnectionFactory.getBootstrapConnection();
		try {
			exec(conn);
		} catch(Throwable thr) {
			throw(new RuntimeException(thr));
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	public static void exec(Connection conn) {
		InputStream is = null;
		try {
			String dbName = AppParams.getDatabaseName();
			log.info("Using: " + dbName);
			Database.update("use " + dbName, conn);
			log.info("Running script...");
			is = FileUtil.getInputStream(FILE_NAME);
			String sqlString = updateSql(is);
			Database.executeSqlScript(sqlString, conn);
			log.info("Done running script.");
			log.info("Done creating database tables.");
		} finally {
			try {
				is.close();
			} catch(Exception exp) {
				throw new RuntimeException(exp);
			}
		}
	}
	
	private static String updateSql(InputStream is) {
		String str = FileUtil.getAsString(is);
		str = str.replaceAll("@cdmDatabaseSchema.", "");
		return str;
	}
	
}
