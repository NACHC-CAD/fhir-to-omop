package org.nachc.tools.fhirtoomop.util.postgres.exporttables;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportTableForPostgres {

	private static final String OUTPUT_DIR = AppParams.getExportDir();

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getUserConnection();
		try {
			exec("vocabulary", conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

	public static void exec(String tableName, Connection conn) {
		log.info("Writing table: " + tableName);
		String sqlString = getSqlString(tableName);
		Database.update(sqlString, conn);
		log.info("Done writing table.");
	}

	private static String getSqlString(String tableName) {
		String schemaName = AppParams.getFullySpecifiedCdmSchemaName();
		String rtn = "COPY @schemaName.@tableName TO '@outputDir/@tableName.csv' DELIMITER ',' CSV HEADER";
		rtn = rtn.replaceAll("@schemaName", schemaName);
		rtn = rtn.replaceAll("@tableName", tableName);
		rtn = rtn.replaceAll("@outputDir", OUTPUT_DIR);
		return rtn;
	}

}
