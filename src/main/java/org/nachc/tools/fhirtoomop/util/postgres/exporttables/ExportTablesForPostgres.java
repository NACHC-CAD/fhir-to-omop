package org.nachc.tools.fhirtoomop.util.postgres.exporttables;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.postgres.PostgresDatabaseConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportTablesForPostgres {

	public static void main(String[] args) {
		Connection conn = PostgresDatabaseConnectionFactory.getUserConnection();
		try {
//			exportAllCdmTables(conn);
//			exportDataTables(conn);
			exportVocabTables(conn);
		} finally {
			Database.close(conn);
		}
	}
	
	public static void exportAllCdmTables(Connection conn) {
		List<String> tableNames = CdmTablesForPostgres.getAllCdmTables(conn);
		export(tableNames, conn);
		log.info("Done with Export All Cdm Tables.");
	}
	
	public static void exportDataTables(Connection conn) {
		List<String> tableNames = CdmTablesForPostgres.getDataTables(conn);
		export(tableNames, conn);
		log.info("Done with Export Cdm Data Tables.");
	}
	
	public static void exportVocabTables(Connection conn) {
		List<String> tableNames = CdmTablesForPostgres.getVocabTables(conn);
		export(tableNames, conn);
		log.info("Done with Export Vocab Tables.");
	}
	
	private static void export(List<String> tableNames, Connection conn) {
		// do the export
		log.info("Doing export...");
		String dirName = AppParams.getExportDir();
		File dir = new File(dirName);
		if(dir.exists()) {
			FileUtil.rmdir(dir);
		}
		FileUtil.mkdirs(dir);
		for(String tableName : tableNames) {
			ExportTableForPostgres.exec(tableName, conn);
		}
		// create the zip file
		log.info("Creating zip file (this can take several minutes for large databases and/or if you've included a full vocabulary)...");
		String exportDirName = AppParams.getExportDir();
		String zipFileName = "export.zip";
		File zipFileDir = new File(exportDirName).getParentFile();
		ZipUtil.createZip(dir, zipFileDir, zipFileName);
		log.info("Done with export.");
	}
	
}
