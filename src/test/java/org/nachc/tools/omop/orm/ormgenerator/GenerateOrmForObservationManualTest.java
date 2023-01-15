package org.nachc.tools.omop.orm.ormgenerator;

import java.io.File;
import java.sql.Connection;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.codeGenerator.dvo.DvoGenerator;
import org.yaorma.codeGenerator.impl.mssqlserver.MsSqlServerOrmCodeGenerator;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenerateOrmForObservationManualTest {

	public static void main(String[] args) throws Exception {
		log.info("Generating person Dvo...");
		Connection conn = OmopDatabaseConnectionFactory.getOmopConnection();
		try {
			String schemaName = AppParams.getDbName();
			String packageName = "org.nachc.tools.omop.orm.ormgenerator.dvo";
			File destDir = FileUtil.getFromProjectRoot("/src/test/java/org/nachc/tools/omop/orm/ormgenerator/dvo");
			DvoGenerator dvo = new DvoGenerator("OBSERVATION", schemaName, conn, new MsSqlServerOrmCodeGenerator());
			dvo.createDvo(destDir, packageName);
		} finally {
			log.info("Closing database connection...");
			Database.closeConnection(conn);
		}
		log.info("Done.");
	}
	
}
