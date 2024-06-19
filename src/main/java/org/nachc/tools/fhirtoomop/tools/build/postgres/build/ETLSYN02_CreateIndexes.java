package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ETLSYN02_CreateIndexes {

	private static final String PATH = "/postgres/build/synthea/indexes/create-etl-synthea-indexes.sql";
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating INDEXES for ETL-Synthea");
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		log.info("Got connection...");
		try {
			String sqlString = FileUtil.getAsString(PATH);
			String schemaName = AppParams.getDatabaseName();
			sqlString = sqlString.replace("@cdm_schema", schemaName);
			log.info("SQLSTRING: \n\n" + sqlString + "\n\n");
			log.info("Creating indexes from script...");
			Database.executeSqlScript(sqlString, conn);
			log.info("Done creating indexes");
		} finally {
			Database.close(conn);
		}
		log.info("Done creating INDEXES for ETL-Synthea");
	}

}
