package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FHIR05_CreateSequencesForPrimaryKeys {

	private static final String FILE_NAME = "/postgres/build/FHIR05_CreateSequencesForPrimaryKeys.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		try {
			String dbName = AppParams.getDatabaseName();
			log.info("Running script...");
			InputStream is = FileUtil.getInputStream(FILE_NAME);
			Database.executeSqlScript(is, conn);
			log.info("Done running script.");
			log.info("Done creating sequences for primary keys.");
		} finally {
			Database.close(conn);
		}
	}

}
