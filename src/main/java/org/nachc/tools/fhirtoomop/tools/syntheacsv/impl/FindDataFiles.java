package org.nachc.tools.fhirtoomop.tools.syntheacsv.impl;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindDataFiles {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
		String dirName = AppParams.getSyntheaCsvFilesDir();
		String url = AppParams.getSyntheaCsvTestFilesUrl();
		boolean downloadIfNotFound = AppParams.getSyntheaCsvDownloadTestFilesIfNotFound();
		File dir = new File(dirName);
		if((dir.exists() == false || dir.list().length == 0) && downloadIfNotFound) {
			log.info("Doing download...");
			downloadTestFiles();
		} else {
			log.info("Download not required, skipping.");
		}
	}

	private static void downloadTestFiles() {
		String dirName = AppParams.getSyntheaCsvFilesDir();
		String url = AppParams.getSyntheaCsvTestFilesUrl();
		HttpRequestClient client = new HttpRequestClient(url);
		log.info("Getting files...");
		client.doGet();
		File dir = new File(dirName).getParentFile();
		FileUtil.rmdir(dir);
		FileUtil.mkdirs(dir);
		File zipFile = new File(dir, "csv.zip");
		log.info("Writing to: " + FileUtil.getCanonicalPath(zipFile));
		FileUtil.write(client.getResponseInputStream(), zipFile);
		log.info("Unzipping...");
		ZipUtil.unzip(zipFile, zipFile.getParentFile());
		log.info("Done writing.");
	}
	
}
