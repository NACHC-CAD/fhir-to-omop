package org.nachc.tools.fhirtoomop.tools.build.postgres.util.alltables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.Test;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllTablesForSchemaPostgresManualTest {

	private static final String SCHEMA = "demo_cdm";

	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demo_cdm_backup?user=postgres&password=ohdsi";

	@Test
	public void shouldGetTables() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
			String sqlString = "select count(*) as cnt from " + SCHEMA + ".concept;";
			log.info("sqlString: \n" + sqlString);
			String cnt = Database.queryForFirst(sqlString, "cnt", conn);
			log.info("Got " + cnt + " concepts");
			List<String> tableNames = GetAllTablesForSchemaPostgres.getAllTableNamesForSchema(SCHEMA, conn);
			log.info("Got " + tableNames.size() + " tables");
			for(String tableName : tableNames) {
				log.info("\t" + tableName);
			}
			log.info("Got " + tableNames.size() + " tables");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}
