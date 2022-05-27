package org.nachc.tools.fhirtoomop.tools.build.atlas;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallAtlasDatasource {

	private static final String FILE_PATH = "/sqlserver/omop/atlas/init-atlas-create-datasource.sql";
	
	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getOhdsiConnection();
		try {
			String sqlString;
			// delete from webapi.source_daimon
			log.info("Deleting from webapi.source_daimon");
			sqlString = "delete from webapi.source_daimon";
			Database.update(sqlString, conn);
			// delete from webapi.source
			log.info("Deleting from webapi.source");
			sqlString = "delete from webapi.source";
			Database.update(sqlString, conn);
			// create the new records
			log.info("getting sql script...");
			sqlString = FileUtil.getAsString(FILE_PATH);
			log.info("executing script...");
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating data source.");
	}
	
}
