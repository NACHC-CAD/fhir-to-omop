package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A05_CreateCdmSourceRecordInCdmForAtlas {

	private static final String PATH = "/postgres/build/A05_CreateCdmSourceRecordInCdmForAtlas.sql";
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Creating CDM source record in CDM for Atlas record");
		Connection conn = PostgresDatabaseConnectionFactory.getDbConnection();
		log.info("Got connection...");
		try {
			String sqlString = FileUtil.getAsString(PATH);
			sqlString = replace(sqlString, "cdm_source_name");
			sqlString = replace(sqlString, "cdm_source_abbreviation");
			sqlString = replace(sqlString, "cdm_holder");
			sqlString = replace(sqlString, "source_description");
			sqlString = replace(sqlString, "source_documentation_reference");
			sqlString = replace(sqlString, "cdm_etl_reference");
			sqlString = replace(sqlString, "source_release_date");
			sqlString = replace(sqlString, "cdm_release_date");
			sqlString = replace(sqlString, "cdm_version_concept_id");
			sqlString = replace(sqlString, "cdm_version");
			sqlString = replace(sqlString, "vocabulary_version");
			log.info("SQLSTRING: \n\n" + sqlString);
			Database.executeSqlScript(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done creating CDM source record in CDM for Atlas record");
	}

	private static String replace(String sqlString, String name) {
		return sqlString.replace("@" + name, AppParams.get(name));
	}

}
