package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class creates the PostgreSQL database used by Atlas/WebAPI for the Atlas/WebAPI data (i.e. not CDM).  
 *
 */

@Slf4j
public class FHIR04_AddPlaceholderCdmRecords {

	private static final String FILE_PATH = "/postgres/build/FHIR04_AddPlaceholderCdmRecords.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			log.info("getting sql script...");
			log.info("executing script...");
			String sqlString = getSqlString();
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done with init postgres users for Atlas.");
	}

	private static String getSqlString() {
		String sqlString = FileUtil.getAsString(FILE_PATH);
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getDbName());
		return sqlString;
	}

}
