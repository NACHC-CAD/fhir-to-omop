package org.nachc.tools.omop.orm.ormgenerator;

import java.io.File;
import java.sql.Connection;

import org.nachc.tools.fhirtoomop.util.db.connection.OmopDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.codeGenerator.generateOrmForSchema.GenerateOrmForSchema;
import org.yaorma.codeGenerator.impl.mssqlserver.MsSqlServerOrmCodeGenerator;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenerateOrm {

	public static void main(String[] args) {
		generateDvos();
	}

	public static void generateDvos() {
		Connection conn = OmopDatabaseConnectionFactory.getCdmConnection();
		try {
			String schemaName = AppParams.getDatabaseName();
			String packageName = "org.nachc.tools.omop.yaorma.dvo";
			File destDir = FileUtil.getFromProjectRoot("/src/main/java/org/nachc/tools/omop/yaorma/dvo");
			FileUtil.clearContents(destDir);
			GenerateOrmForSchema.execute(conn, schemaName, packageName, destDir, new MsSqlServerOrmCodeGenerator());
			log.info("Done with generate dvos.");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			OmopDatabaseConnectionFactory.close(conn);
		}
		log.info("Done.");
	}

}
