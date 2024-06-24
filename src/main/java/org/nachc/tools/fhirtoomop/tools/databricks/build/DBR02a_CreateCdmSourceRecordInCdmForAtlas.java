package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.connection.DatabricksConnectionFactory;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
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
		sqlString = replace(sqlString, "cdm_source_name");
		sqlString = replace(sqlString, "cdm_source_abbreviation");
		sqlString = replace(sqlString, "cdm_holder");
		sqlString = replace(sqlString, "source_description");
		sqlString = replace(sqlString, "source_documentation_reference");
		sqlString = replace(sqlString, "cdm_etl_reference");
		sqlString = replace(sqlString, "source_release_date");
		sqlString = replace(sqlString, "cdm_release_date");
		sqlString = replace(sqlString, "cdm_version_concept_id");
		sqlString = replace(sqlString, "CdmVersion");
		sqlString = replace(sqlString, "vocabulary_version");
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
