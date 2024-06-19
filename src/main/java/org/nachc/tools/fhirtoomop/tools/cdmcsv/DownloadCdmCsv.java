package org.nachc.tools.fhirtoomop.tools.cdmcsv;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadCdmCsv {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Looking for default terminology...");
		boolean downloadIfNotFound = AppParams.getCdmCsvDownloadIfNotFound();
		File rootDir = new File(AppParams.getCdmCsvZipFileLocation());
		if (rootDir.exists() == false && downloadIfNotFound == true) {
			Timer timer = new Timer();
			timer.start();
			String msg = "\n\n";
			msg += "* * * \n";
			msg += "*  \n";		
			msg += "* ! ! ! DOWNLOADING DEFAULT FILE ! ! ! \n";
			msg += "* (files not found and downloadIfNotFound has been set to true in properties file)  \n";
			msg += "*  \n";		
			msg += "* * *  \n";
			msg += "\n\n";
			log.info(msg);
			log.info("Files will be downloaded to: \n" + rootDir);
			TimeUtil.sleep(5);
			log.info("Starting download...");
			FileUtil.rmdir(rootDir);
			FileUtil.mkdirs(rootDir);
			String url = AppParams.getCdmCsvDownloadUrl();
			HttpRequestClient client = new HttpRequestClient(url);
			log.info("Getting file from: " + client.getUrl());
			client.doGet();
			log.info("Got file, writing to disc (this might take several minutes)...");
			String fileName = AppParams.getCdmCsvZipFileName();
			File zipFile = new File(rootDir, fileName);
			FileUtil.createNewFile(zipFile);
			log.info("Writing file to: " + FileUtil.getCanonicalPath(zipFile));
			FileUtil.write(client.getResponseInputStream(), zipFile);
			log.info("Unzipping file");
			ZipUtil.unzip(zipFile, rootDir);
			timer.stop();
			log.info("Time to download: " + timer.getElapsedString());
			log.info("Done getting file.");
			zipFile.delete();
		}
	}

}


