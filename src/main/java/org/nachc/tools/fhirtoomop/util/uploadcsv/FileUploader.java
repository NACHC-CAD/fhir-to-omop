package org.nachc.tools.fhirtoomop.util.uploadcsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.tools.build.impl.fileupload.UploadCsvFilesFromZipForPostgres;
import org.nachc.tools.fhirtoomop.util.db.connection.BootstrapConnectionFactory;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public abstract class FileUploader {
	
	protected List<String> ignoreList = new ArrayList<String>();
	
	protected List<String> success = new ArrayList<String>();
	
	protected List<String> failure = new ArrayList<String>();
	
	protected List<String> ignored = new ArrayList<String>();
	
	protected boolean invertIgnore = false;

	protected abstract void uploadFile(String dbName, String schemaName, File file, Connection conn);

	public void exec(String dbName, String schemaName, InputStream is, File outputDir, Connection conn) {
		try {
			log.info("Unzipping file...");
			ZipUtil.unzip(is, outputDir);
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(outputDir));
			// get the list of files
			File srcDir = outputDir;
			File[] files = srcDir.listFiles();
			log.info("Got " + files.length + " files...");
			for(File file : files) {
				if(file.isDirectory() == false && isZip(file) == false) {
					uploadFile(dbName, schemaName, file, conn);
				}
			}
			logOutcomes();
			log.info("Done uploading files.");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	protected boolean ignore(String tableName) {
		boolean rtn = false;
		if(this.ignoreList.contains(tableName.toUpperCase())) {
			rtn = true;
		} else {
			rtn = false;
		}
		if(invertIgnore == true) {
			rtn = !rtn;
		}
		return rtn;
	}
	
	protected boolean isZip(File file) {
		String name = file.getName().toLowerCase();
		if(name.endsWith(".zip") == true) {
			return true;
		} else {
			return false;
		}
	}
	
	//
	// log the outcome
	//
	
	protected void logOutcomes() {
		String msg;
		msg = "\n--------------------\n";
		msg += "Success: The following tables were successfully uploaded. \n";
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
			msg += "! ! ! SOME TABLES FAILED ! ! !\n";
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
	}

}
