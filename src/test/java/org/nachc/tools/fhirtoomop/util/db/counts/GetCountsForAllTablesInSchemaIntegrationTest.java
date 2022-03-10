package org.nachc.tools.fhirtoomop.util.db.counts;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetCountsForAllTablesInSchemaIntegrationTest {

	@Test
	public void shouldGetCounts() {
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			String schemaName = AppParams.getFullyQualifiedDbName();
			Data data = GetCountsForAllTablesInSchema.getCountsForSchema(schemaName, conn);
			log.info("Got data for " + data.size() + " tables.");
			for (Row row : data) {
				String rowCount = StringUtils.rightPad(row.get("rowCount"), 12);
				log.info("\t" + rowCount + row.get("tableName"));
			}
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
