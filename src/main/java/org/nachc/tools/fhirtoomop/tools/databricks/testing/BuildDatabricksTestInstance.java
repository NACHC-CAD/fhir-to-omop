package org.nachc.tools.fhirtoomop.tools.databricks.testing;

import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.databricks.build.CreateDatabricksSchemaFromCdmDdl;

public class BuildDatabricksTestInstance {

	//TODO: FINISH THIS THOUGHT
	
	public static void exec(String schemaName, Connection conn) {
		CreateDatabricksSchemaFromCdmDdl.exec(schemaName, conn);
	}
	
}
