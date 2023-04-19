package org.nachc.tools.fhirtoomop.tools.build.postgres.util.exportcsv;

import java.io.File;
import java.sql.Connection;

import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportTableAsCsv {

	public static void export(String schemaName, String tableName, File rootDir, Connection conn, boolean nestInFolder) {
		File file = new File(FileUtil.getCanonicalPath(rootDir));
		if(nestInFolder == true) {
			file = new File(FileUtil.getCanonicalPath(file), tableName);
		}
		FileUtil.rmdir(file);
		FileUtil.mkdirs(file);
		file = new File(file, tableName + ".csv");
		String fileName = FileUtil.getCanonicalPath(file);
		log.info("Writing to file: " + FileUtil.getCanonicalPath(file));
		String sqlString = "";
		sqlString += "copy " + schemaName + "." + tableName + " to '" + fileName + "' DELIMITER ',' CSV HEADER \n";
		log.info("sqlString \n" + sqlString);
		Database.update(sqlString, conn);
		log.info("Done writing table: " + tableName);
	}
	
}
