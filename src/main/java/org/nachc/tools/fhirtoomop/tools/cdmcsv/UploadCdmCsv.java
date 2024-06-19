package org.nachc.tools.fhirtoomop.tools.cdmcsv;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.db.datatables.VocabularyTablesList;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadCdmCsv {

	private ArrayList<String> success = new ArrayList<String>();

	private ArrayList<String> failure = new ArrayList<String>();

	private ArrayList<String> ignored = new ArrayList<String>();

	private List<String> ignoreList = new ArrayList<>();

	public static void main(String[] args) {
		UploadCdmCsv upload = new UploadCdmCsv();
		String dirName = AppParams.getCdmCsvZipFileLocation();
		Connection conn = BootstrapConnectionFactory.getBootstrapConnection();
		String databaseName = AppParams.getDatabaseName();
		Database.update("use " + databaseName, conn);
		upload.ignoreList = VocabularyTablesList.getTablesList();
		upload.uploadDir(dirName, conn);
	}

	public void uploadDir(String dirName, Connection conn) {
		File dir = new File(dirName);
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			if (file.isDirectory() == true) {
				continue;
			} else {
				uploadFile(conn, file);
			}
		}
		String msg;
		msg = "\n--------------------\n";
		msg += "Succefully uploaded \n";
		for (String str : success) {
			msg += "  " + str + "\n";
		}
		msg += "--------------------\n";
		log.info(msg);
		if (ignored.size() > 0) {
			msg = "\n--------------------\n";
			msg += "The following files were ignored: \n";
			for (String str : ignored) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "No files were ignored.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
		if (failure.size() > 0) {
			msg = "\n--------------------\n";
			msg += "! ! ! SOME FILES FAILED TO UPLOAD ! ! !\n";
			for (String str : failure) {
				msg += "  " + str + "\n";
			}
			msg += "--------------------\n";
		} else {
			msg = "\n--------------------\n";
			msg += "There were no errors.\n";
			msg += "--------------------\n";
		}
		log.info(msg);
		log.info("Done with upload.");
	}

	private void uploadFile(Connection conn, File file) {
		try {
			String fileName = file.getName();
			String tableName = fileName.substring(0, fileName.indexOf("."));
			if (ignoreTable(tableName) == true) {
				log.info("Ignoring: " + tableName);
				ignored.add(FileUtil.getCanonicalPath(file));
			} else {
				log.info("-----------");
				log.info("Uploading file: " + FileUtil.getCanonicalPath(file));
				log.info("Table Name: " + tableName);
				String sqlString = "";
				sqlString += "BULK INSERT " + tableName + " \n";
				sqlString += "FROM '" + FileUtil.getCanonicalPath(file) + "' \n";
				sqlString += "WITH \n";
				sqlString += "( \n";
				sqlString += "    FIELDTERMINATOR = ',', \n";
				sqlString += "    ROWTERMINATOR = '\n', \n";
				sqlString += "    FIRSTROW = 2, \n";
				sqlString += "    FORMAT = 'CSV', \n";
				sqlString += "    TABLOCK \n";
				sqlString += ") \n";
				Database.update(sqlString, conn);
				this.success.add(FileUtil.getCanonicalPath(file));
				log.info("Done with file: " + FileUtil.getCanonicalPath(file));
			}
		} catch (Exception exp) {
			log.info("Could not upload file: " + FileUtil.getCanonicalPath(file));
			log.info("Error message: \n" + exp.getMessage());
			this.failure.add(FileUtil.getCanonicalPath(file));
		}
	}

	private boolean ignoreTable(String tableName) {
		if (this.ignoreList.contains(tableName.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

}
