package org.nachc.tools.fhirtoomop.tools.syntheacsv.impl;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindDriver {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
		log.info("Looking for driver...");
		String dirName = AppParams.getSyntheaCsvJdbcLocation();
		String fileName = AppParams.getSyntheaCsvJdbcDriverName();
		String url = AppParams.getSyntheaCsvJdbcDownloadUrl();
		boolean downloadIfNotFound = AppParams.getSyntheaCsvDownloadJdbcDriverIfNotFound();
		File dir = new File(dirName);
		if((dir.exists() == false || dir.listFiles().length == 0) && downloadIfNotFound == true) {
			log.info("Driver not found, doing download...");
			downloadDriver(url, dir, fileName);
			log.info("Done with download.");
		} else {
			log.info("Driver found, skipping download.");
		}
		log.info("Done with FindDriver method.");
	}

	private static void downloadDriver(String url, File dir, String fileName) {
		log.info("Dowloading driver...");
		FileUtil.rmdir(dir);
		FileUtil.mkdirs(dir);
		File target = new File(dir, fileName);
		HttpRequestClient client = new HttpRequestClient(url);
		client.doGet();
		log.info("Writing file...");
		client.writeResponseToFile(target);
	}
	
}
