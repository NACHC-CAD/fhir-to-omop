package org.nachc.tools.fhirtoomop.util.sqlserver;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.win.bat.RunBatFile;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportTable {

	private static final String FILE_PATH = "C:\\temp\\polites\\sql-server\\export";
	
	private static final String FILE_NAME = "sql-server-export.bat";
	
	public static void main(String[] args) {
		String exportDir = AppParams.getExportDir();
		File dir = new File(exportDir);
		if(dir.exists()) {
			FileUtil.rmdir(dir);
		}
		FileUtil.mkdirs(dir);
		exec("person");
		log.info("Done.");
	}
	
	public static void exec(String tableName) {
		log.info("EXPORTING TABLE: " + tableName);
		log.info("Cleaning up dirs...");
		File dir = new File(FILE_PATH);
		if(dir.exists()) {
			FileUtil.rmdir(dir);
		}
		FileUtil.mkdirs(dir);
		log.info("Writing bat file...");
		String cmd = getCmd(tableName);
		File file = writeFile(cmd);
		log.info("Executing export for table: " + tableName.toUpperCase());
		RunBatFile.exec(file);
		log.info("Done writing table: " + tableName);
	}
	
	private static String getCmd(String tableName) {
		String cmd = FileUtil.getAsString("/sqlserver/export/export-table.bat");
		cmd = cmd.replace("@fullySpecifiedSchemaName", AppParams.getFullySpecifiedCdmSchemaName());
		cmd = cmd.replace("@tableName", tableName);
		cmd = cmd.replace("@pathToOutputFile", AppParams.getExportDir() + "\\" + tableName + ".csv");
		cmd = cmd.replace("@serverName", AppParams.getServerName());
		cmd = cmd.replace("@uid", AppParams.getUid());
		cmd = cmd.replace("@pwd", AppParams.getPwd());
		log.info("CMD: \n" + cmd);	
		return cmd;
	}
	
	private static File writeFile(String cmd) {
		File file = new File(FILE_PATH, FILE_NAME);
		FileUtil.write(cmd, file);
		return file;
	}
	
}
