package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VOC00_DownloadTerminology {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		log.info("Looking for default terminology...");
		boolean terminologyDownloadIfNotFound = "true".equalsIgnoreCase(AppParams.get("terminologyDownloadIfNotFound"));
		File terminologyRootDir = new File(AppParams.get("terminologyRootDir"));
		if (terminologyRootDir.exists() == false && terminologyDownloadIfNotFound == true) {
			Timer timer = new Timer();
			timer.start();
			log.info("DOWNLOADING DEFAULT TERMINOLOGY (Terminology files not found and downloadIfNotFound has been set to true in properties file)");
			FileUtil.mkdirs(terminologyRootDir.getParentFile());
			String url = AppParams.get("terminologyDownloadUrl");
			HttpRequestClient client = new HttpRequestClient(url);
			log.info("Getting file from: " + client.getUrl());
			client.doGet();
			log.info("Got file, writing to disc (this takes several minutes)...");
			String zipFileName = terminologyRootDir.getName() + ".zip";
			File zipFile = new File(terminologyRootDir.getParent(), zipFileName);
			FileUtil.createNewFile(zipFile);
//			client.writeResponseToFile(zip);
			log.info("Writing file to: " + FileUtil.getCanonicalPath(zipFile));
			FileUtil.write(client.getResponseInputStream(), zipFile);
			log.info("Unzipping file");
			ZipUtil.unzip(zipFile, terminologyRootDir);
			timer.stop();
			log.info("Time to download terminology: " + timer.getElapsedString());
			log.info("Got terminology files!");
		}
	}

}
