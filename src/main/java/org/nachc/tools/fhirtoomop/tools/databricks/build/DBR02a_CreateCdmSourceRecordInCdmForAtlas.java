package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBR02a_CreateCdmSourceRecordInCdmForAtlas {

	private static final String PATH = "/databricks/build/DBR02a_CreateCdmSourceRecordInCdmForAtlas.sql";

	public static void main(String[] args) {
		Connection conn = DatabricksConnectionFactory.getConnection();
		try {
			exec(conn);
		} finally {
			Database.close(conn);
		}

	}

	public static void exec(Connection conn) {
		log.info("Creating CDM source record in CDM for Atlas record");
		log.info("Got connection...");
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
		log.info("SQLSTRING: \n\n" + sqlString);
		Database.executeSqlScript(sqlString, conn);
		log.info("Done creating CDM source record in CDM for Atlas record");
	}

	private static String replace(String sqlString, String name) {
		sqlString = sqlString.replace("@" + name, DatabricksProperties.get(name));
		sqlString = sqlString.replace("<ohdsiDbName>", DatabricksProperties.getSchemaName());
		return sqlString;
	}

}
