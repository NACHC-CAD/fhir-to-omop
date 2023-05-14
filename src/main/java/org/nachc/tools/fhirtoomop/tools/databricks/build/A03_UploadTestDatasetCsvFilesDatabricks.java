package org.nachc.tools.fhirtoomop.tools.databricks.build;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.properties.DatabricksProperties;
import org.nachc.tools.fhirtoomop.util.databricks.upload.UploadCsvToDatabricks;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A03_UploadTestDatasetCsvFilesDatabricks {

	private static final File WORKING_DIR = new File("C:\\temp\\demo_cdm");

	private static final InputStream ZIP_SRC = FileUtil.getInputStream("/databricks/demo_cdm.zip");
	
	private static final String DATABRICKS_FILE_ROOT = "/ohdsi/ponos/demo_cdm";

	public static void main(String[] args) {
		log.info("Uploading csv files...");
		String schemaName = DatabricksProperties.getSchemaName();
		exec(schemaName);
		log.info("Done.");
	}

	public static void exec(String schemaName) {
		// delete existing files
		log.info("Deleting existing files...");
		log.info("Doing delete...");
		log.info("Databricks file dir: " + DATABRICKS_FILE_ROOT);
		DeleteCsvFromDatabricks.exec(DATABRICKS_FILE_ROOT, true);
		// write files
		log.info("Writing zip file to working dir...");
		// clean up dirs
		FileUtil.rmdir(WORKING_DIR);
		FileUtil.mkdirs(WORKING_DIR);
		// copy and extract the zip file
		File zipFile = new File(WORKING_DIR, "demo_cdm.zip");
		FileUtil.write(ZIP_SRC, zipFile);
		ZipUtil.unzip(zipFile, WORKING_DIR);
		// get the list of files
		File srcDir = new File(WORKING_DIR, "demo_cdm");
		List<File> dirs = FileUtil.list(srcDir);
		log.info("Got " + dirs.size() + " dirs");
		for (File dir : dirs) {
			processDir(dir, schemaName);
		}
		log.info("Got " + dirs.size() + " dirs");
		log.info("Done uploading test data.");
	}

	private static void processDir(File dir, String schemaName) {
		log.info("Start processing dir: " + FileUtil.getCanonicalPath(dir));
		for (File file : dir.listFiles()) {
			uploadFile(file, schemaName);
		}
		log.info("Done processing dir: " + FileUtil.getCanonicalPath(dir));
	}

	private static void uploadFile(File file, String schemaName) {
		String fileName = file.getName();
		String parentName = file.getParentFile().getName();
		String databricksFilePath = DATABRICKS_FILE_ROOT + "/" + parentName + "/" + fileName;
		log.info("Doing upload for file...");
		log.info(FileUtil.getCanonicalPath(file));
		log.info("Path: " + databricksFilePath);
		log.info("Uploading csv file...");
		UploadCsvToDatabricks.exec(databricksFilePath, file, schemaName);
		log.info("Creating data table...");
		log.info("Done with upload");
	}

}
