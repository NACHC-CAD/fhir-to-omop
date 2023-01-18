package org.nachc.tools.fhirtoomop.util.db.counts;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.yaorma.database.Database;

public class GetCountForTable {

	public static int exec(String tableName) {
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			int cnt = Database.count(tableName, conn);
			return cnt;
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}

}
