package org.nachc.tools.fhirtoomop.tools.mysql;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.mysql.MySqlDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.mysql.util.ConvertAllTablesToMyIsam;
import org.yaorma.database.Database;

public class ConvertAllTablesInSchemaToMyIsamTool {

	private static final String SCHEMA = "synthe_omop";
	
	public static void exec() {
		Connection conn = MySqlDatabaseConnectionFactory.getSyntheaConnection();
		try {
			new ConvertAllTablesToMyIsam().exec(SCHEMA, conn);
		} finally {
			Database.close(conn);
		}
	}

}
