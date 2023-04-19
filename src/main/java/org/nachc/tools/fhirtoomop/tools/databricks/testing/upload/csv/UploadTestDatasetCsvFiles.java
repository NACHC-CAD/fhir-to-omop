package org.nachc.tools.fhirtoomop.tools.databricks.testing.upload.csv;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.databricks.delete.DeleteCsvFromDatabricks;
import org.nachc.tools.fhirtoomop.util.databricks.upload.UploadCsvToDatabricks;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadTestDatasetCsvFiles {

	private static final File WORKING_DIR = new File("C:\\temp\\demo_cdm");

	private static final InputStream ZIP_SRC = FileUtil.getInputStream("/databricks/demo_cdm.zip");

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		// delete existing files
		log.info("Deleting existing files...");
		log.info("Doing delete...");
		String databricksFileRootDir = "/ohdsi/demo_cdm";
		log.info("Databricks file dir: " + databricksFileRootDir);
		DeleteCsvFromDatabricks.exec(databricksFileRootDir, true);
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
			processDir(dir);
		}
		log.info("Got " + dirs.size() + " dirs");
		log.info("Done.");
	}

	private static void processDir(File dir) {
		log.info("Start processing dir: " + FileUtil.getCanonicalPath(dir));
		for (File file : dir.listFiles()) {
			uploadFile(file);
		}
		log.info("Done processing dir: " + FileUtil.getCanonicalPath(dir));
	}

	private static void uploadFile(File file) {
		String fileName = file.getName();
		String parentName = file.getParentFile().getName();
		String databricksFilePath = "/ohdsi/demo_cdm/" + parentName + "/" + fileName;
		log.info("Doing upload for file...");
		log.info(FileUtil.getCanonicalPath(file));
		log.info("Path: " + databricksFilePath);
		UploadCsvToDatabricks.exec(databricksFilePath, file);
		log.info("Done with upload");
	}

}