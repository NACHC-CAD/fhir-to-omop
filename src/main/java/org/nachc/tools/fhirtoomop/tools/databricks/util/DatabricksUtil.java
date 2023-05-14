package org.nachc.tools.fhirtoomop.tools.databricks.util;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.A01_CreateCdmDatabaseDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A02_CreateCdmDatabaseObjectsDatabricks;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A03_UploadTestDatasetCsvFilesDatabricks;

public class DatabricksUtil {

	public static void createDatabricksCdmSchema(String schemaName, Connection conn) {
		A01_CreateCdmDatabaseDatabricks.exec(schemaName, conn);
	}
	
	public static void createDatabricksCdmSchemaObjectsFromCdmDdl(String schemaName, Connection conn) {
		A02_CreateCdmDatabaseObjectsDatabricks.exec(schemaName, conn);
	}
	
	public static void uploadTestDatasetCsvFiles(String schemaName) {
		A03_UploadTestDatasetCsvFilesDatabricks.exec(schemaName);
	}

}
