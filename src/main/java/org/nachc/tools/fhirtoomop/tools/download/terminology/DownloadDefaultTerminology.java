package org.nachc.tools.fhirtoomop.tools.download.terminology;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.TimeUtil;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadDefaultTerminology {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Looking for default terminology...");
		boolean terminologyDownloadIfNotFound = "true".equalsIgnoreCase(AppParams.getTerminologyDownloadIfNotFound());
		String rootDir = AppParams.getTerminologyRootDir();
		File terminologyRootDir = new File(rootDir);
		if (terminologyRootDir.exists() == false && terminologyDownloadIfNotFound == true) {
			Timer timer = new Timer();
			timer.start();
			String msg = "\n\n";
			msg += "* * * \n";
			msg += "*  \n";		
			msg += "* ! ! ! DOWNLOADING DEFAULT TERMINOLOGY ! ! ! \n";
			msg += "* (Terminology files not found and TerminologyDownloadIfNotFound has been set to true in properties file)  \n";
			msg += "*  \n";		
			msg += "* * *  \n";
			msg += "\n\n";
			log.info(msg);
			log.info("Terminology files will be downloaded to: \n" + terminologyRootDir);
			TimeUtil.sleep(5);
			log.info("Starting download...");
			FileUtil.rmdir(terminologyRootDir);
			FileUtil.mkdirs(terminologyRootDir);
			String url = AppParams.getTerminologyDownloadUrl();
			HttpRequestClient client = new HttpRequestClient(url);
			log.info("Getting file from: " + client.getUrl());
			client.doGet();
			log.info("Got file, writing to disc (this takes several minutes)...");
			String zipFileName = "default-terminology.zip";
			File zipFile = new File(terminologyRootDir, zipFileName);
			FileUtil.createNewFile(zipFile);
			log.info("Writing file to: " + FileUtil.getCanonicalPath(zipFile));
			FileUtil.write(client.getResponseInputStream(), zipFile);
			log.info("Unzipping file");
			ZipUtil.unzip(zipFile, terminologyRootDir);
			timer.stop();
			log.info("Time to download terminology: " + timer.getElapsedString());
			log.info("Got terminology files!");
			log.info("Done with download of terminology.");
		}
	}

}
