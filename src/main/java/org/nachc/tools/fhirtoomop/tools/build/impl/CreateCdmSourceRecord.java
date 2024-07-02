package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.InputStream;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateCdmSourceRecord {

	private static final String PATH = "/sqlserver/omop/5.4/cdm/create-cdm-source-record.sql";
	
	public static void exec(Connection conn) {
		String sqlString = FileUtil.getAsString(PATH);
		sqlString = sqlString.replace("@cdm_source_name", AppParams.getCdmSourceName());
		sqlString = sqlString.replace("@cdm_source_abbreviation", AppParams.getCdmSourceAbbreviation());
		sqlString = sqlString.replace("@cdm_holder", AppParams.getCdmHolder());
		sqlString = sqlString.replace("@source_description", AppParams.getCdmSourceDescription());
		sqlString = sqlString.replace("@source_documentation_reference", AppParams.getSourceDocumentationReference());
		sqlString = sqlString.replace("@cdm_etl_reference", AppParams.getCdmEtlReference());
		sqlString = sqlString.replace("@source_release_date", AppParams.getSourceReleaseDate());
		sqlString = sqlString.replace("@cdm_release_date", AppParams.getCdmReleaseDate());
		sqlString = sqlString.replace("@cdm_version_concept_id", AppParams.getCdmVersionConceptId());
		sqlString = sqlString.replace("@cdm_version", AppParams.getCdmVersion());
		sqlString = sqlString.replace("@vocabulary_version", AppParams.getVocabularyVersion());
		log.info("SQLSTRING: \n\n" + sqlString);
		Database.executeSqlScript(sqlString, conn);
	}

}
