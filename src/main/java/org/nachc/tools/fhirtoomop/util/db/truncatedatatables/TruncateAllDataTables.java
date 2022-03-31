package org.nachc.tools.fhirtoomop.util.db.truncatedatatables;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.counts.GetCountsForAllTablesInSchema;
import org.nachc.tools.fhirtoomop.util.db.datatables.DatatableList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TruncateAllDataTables {

	private static final String[] TABLE_NAMES = DatatableList.getDatatableArray();

	public static void exec() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			String schemaName = AppParams.getFullyQualifiedDbName();
			List<String> tableNames = Arrays.asList(TABLE_NAMES);
			TruncateDataTables.truncateTables(tableNames, conn);
			Database.commit(conn);
			Data data = GetCountsForAllTablesInSchema.getCountsForTables(schemaName, tableNames, conn);
			log.info("\tcnt\ttable_name");
			for (Row row : data) {
				log.info("\t" + row.get("rowCount") + "\t" + row.get("tableName"));
			}
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
	}

}