package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDM03_CreateCdmSourceRecordInCdm {

	private static final String PATH = "/postgres/build/CDM03_CreateCdmSourceRecordInCdmForAtlas.sql";

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Connection conn = PostgresDatabaseConnectionFactory.getCdmConnection();
		log.info("Got connection...");
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}
	}

	public static void exec(Connection conn) {
		log.info("Creating CDM source record in CDM for Atlas record");
		String sqlString = FileUtil.getAsString(PATH);
		sqlString = sqlString.replace("@cdm_source_name", AppParams.getCdmSourceName());
		sqlString = sqlString.replace("@cdm_source_abbreviation", AppParams.getCdmSourceAbbreviation());
		sqlString = sqlString.replace("@cdm_holder", AppParams.getCdmHolder());
		sqlString = sqlString.replace("@source_description", AppParams.getSourceDescription());
		sqlString = sqlString.replace("@source_documentation_reference", AppParams.getSourceDocumentationReference());
		sqlString = sqlString.replace("@cdm_etl_reference", AppParams.getCdmEtlReference());
		sqlString = sqlString.replace("@source_release_date", AppParams.getSourceReleaseDate());
		sqlString = sqlString.replace("@cdm_release_date", AppParams.getCdmReleaseDate());
		sqlString = sqlString.replace("@cdm_version_concept_id", AppParams.getCdmVersionConceptId());
		sqlString = sqlString.replace("@cdm_version", AppParams.getCdmVersion());
		sqlString = sqlString.replace("@vocabulary_version", AppParams.getVocabularyVersion());
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getFullySpecifiedCdmSchemaName());
		log.info("SQLSTRING: \n\n" + sqlString);
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating CDM source record in CDM for Atlas record");
	}

	private static String replace(String sqlString, String name) {
		sqlString = sqlString.replace("@" + name, AppParams.get(name));
		sqlString = sqlString.replace("<ohdsiDbName>", AppParams.getDatabaseName());
		return sqlString;
	}

}
