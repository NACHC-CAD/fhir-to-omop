package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.testing.upload.csv.UploadTestDatasetCsvFiles;
import org.nachc.tools.fhirtoomop.util.databricks.build.CreateDatabricksSchemaFromCdmDdl;

public class DatabricksUtil {

	public static void uploadTestDatasetCsvFiles(Connection conn) {
		UploadTestDatasetCsvFiles.exec(conn);
	}

	public static void createDatabricksSchemaFromCdmDdl(String schemaName, Connection conn) {
		CreateDatabricksSchemaFromCdmDdl.exec(schemaName, conn);
	}
	
}
