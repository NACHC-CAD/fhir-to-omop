package org.nachc.tools.fhirtoomop.util.db.mysql.util.myisam;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableForConvertTablesToMyIsam implements Runnable {

	private String schemaName;
	
	private String tableName;
	
	private Connection conn;
	
	public RunnableForConvertTablesToMyIsam(String schemaName, String tableName) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.conn = OmopDatabaseConnectionFactory.getOmopConnection();
	}
	
	@Override
	public void run() {
		String sqlString = "alter table " + schemaName + "." + tableName + " ENGINE = MyISAM";
		try {
			log.info(sqlString);
			Database.update(sqlString, conn);
			log.info("Done with " + tableName);
		} catch(Exception exp) {
			log.info("COULD NOT EXECUTE SQL: \n\n" + sqlString + "\n\n");;
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}

}
