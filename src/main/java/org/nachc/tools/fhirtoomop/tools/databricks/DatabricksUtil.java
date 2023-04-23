package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.createschema.CreateDatabricksCsvSchema;
import org.nachc.tools.fhirtoomop.tools.databricks.createschemafromcdmddl.CreateDatabricksSchemaFromCdmDdl;
import org.nachc.tools.fhirtoomop.tools.databricks.uploaddemocdmfromcsv.UploadTestDatasetCsvFiles;

public class DatabricksUtil {

	public static void createDatabricksCsvDatabaseSchema(Connection conn) {
		CreateDatabricksCsvSchema.exec(conn);
	}
	
	public static void createDatabricksSchemaFromCdmDdl(Connection conn) {
		CreateDatabricksSchemaFromCdmDdl.exec(conn);
	}
	
	public static void uploadTestDatasetCsvFiles() {
		UploadTestDatasetCsvFiles.exec();
	}

}
