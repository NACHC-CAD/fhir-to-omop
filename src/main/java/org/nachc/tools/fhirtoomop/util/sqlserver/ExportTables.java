package org.nachc.tools.fhirtoomop.util.sqlserver;

import java.io.File;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportTables {

	public static void main(String[] args) {
//		exportAllCdmTables();
		exportDataTables();
//		exportVocabTables();
	}
	
	public static void exportAllCdmTables() {
		List<String> tableNames = CdmTables.getAllCdmTables();
		export(tableNames);
		log.info("Done with Export All Cdm Tables.");
	}
	
	public static void exportDataTables() {
		List<String> tableNames = CdmTables.getDataTables();
		export(tableNames);
		log.info("Done with Export Cdm Data Tables.");
	}
	
	public static void exportVocabTables() {
		List<String> tableNames = CdmTables.getVocabTables();
		export(tableNames);
		log.info("Done with Export Vocab Tables.");
	}
	
	private static void export(List<String> tableNames) {
		// do the export
		log.info("Doing export...");
		String dirName = AppParams.getExportDir();
		File dir = new File(dirName);
		if(dir.exists()) {
			FileUtil.rmdir(dir);
		}
		FileUtil.mkdirs(dir);
		for(String tableName : tableNames) {
			ExportTable.exec(tableName);
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
