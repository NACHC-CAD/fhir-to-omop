package org.nachc.tools.fhirtoomop.tools.databricks;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.tools.databricks.createschema.CreateDatabricksSchema;
import org.nachc.tools.fhirtoomop.tools.databricks.createschemafromcdmddl.CreateDatabricksSchemaObjectsFromCdmDdl;
import org.nachc.tools.fhirtoomop.tools.databricks.uploaddemocdmfromcsv.UploadTestDatasetCsvFiles;

public class DatabricksUtil {

	public static void createDatabricksSchema(Connection conn) {
		CreateDatabricksSchema.exec(conn);
	}
	
	public static void createDatabricksSchemaObjectsFromCdmDdl(Connection conn) {
		CreateDatabricksSchemaObjectsFromCdmDdl.exec(conn);
	}
	
	public static void uploadTestDatasetCsvFiles() {
		UploadTestDatasetCsvFiles.exec();
	}

}
