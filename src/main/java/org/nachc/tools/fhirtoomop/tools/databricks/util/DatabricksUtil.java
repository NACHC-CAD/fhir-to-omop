package org.nachc.tools.fhirtoomop.tools.databricks.util;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.build.A01_CreateDatabricksCdmSchema;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A02_CreateDatabricksCdmSchemaObjectsFromCdmDdl;
import org.nachc.tools.fhirtoomop.tools.databricks.build.A03_UploadTestDatasetCsvFiles;

public class DatabricksUtil {

	public static void createDatabricksCdmSchema(String schemaName, Connection conn) {
		A01_CreateDatabricksCdmSchema.exec(schemaName, conn);
	}
	
	public static void createDatabricksCdmSchemaObjectsFromCdmDdl(Connection conn) {
		A02_CreateDatabricksCdmSchemaObjectsFromCdmDdl.exec(conn);
	}
	
	public static void uploadTestDatasetCsvFiles() {
		A03_UploadTestDatasetCsvFiles.exec();
	}

}
