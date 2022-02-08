package org.nachc.tools.fhirtoomop.util.db.mysql.util.myisam;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
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
		this.conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
	}
	
	@Override
	public void run() {
		try {
			String sqlString = "alter table " + schemaName + "." + tableName + " ENGINE = MyISAM";
			log.info(sqlString);
			Database.update(sqlString, conn);
			log.info("Done with " + tableName);
		} finally {
			Database.close(conn);
		}
	}

}
