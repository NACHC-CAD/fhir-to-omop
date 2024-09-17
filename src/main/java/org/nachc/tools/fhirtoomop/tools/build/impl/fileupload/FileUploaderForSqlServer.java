package org.nachc.tools.fhirtoomop.tools.build.impl.fileupload;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.uploadcsv.FileUploader;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class FileUploaderForSqlServer extends FileUploader {

	protected void uploadFile(String dbName, String schemaName, File file, Connection conn) {
		String fileName = file.getName();
		String tableName = fileName.substring(0, fileName.indexOf("."));
		String fullName = dbName + "." + schemaName + "." + tableName;
		log.info("-----------");
		log.info("Uploading file: " + FileUtil.getCanonicalPath(file));
		log.info("Table Name: " + fullName);
		if(ignore(tableName) == true) {
			log.info("SKIPPING TABLE: " + fullName);
			this.ignored.add(fullName);
			return;
		}
		try {
			String sqlString = "";
			sqlString += "BULK INSERT " + fullName + " \n";
			sqlString += "FROM '" + FileUtil.getCanonicalPath(file) + "' \n";
			sqlString += "WITH \n";
			sqlString += "( \n";
			sqlString += "    FIELDTERMINATOR = ',', \n";
			sqlString += "    ROWTERMINATOR = '\n', \n";
			sqlString += "    FIRSTROW = 2, \n";
			sqlString += "    FORMAT = 'CSV', \n";
			sqlString += "    TABLOCK \n";
			sqlString += ") \n";		
			Database.update("use " + dbName, conn);
			Database.update(sqlString, conn);
			this.success.add(fullName);
		} catch(Exception exp) {
			log.info("COULD NOT UPLOAD FILE (EXCEPTION OCCURRED): ");
			log.info(exp.getMessage());
			this.failure.add(fullName);
		}
		log.info("Done with file: " + FileUtil.getCanonicalPath(file));
	}

}
