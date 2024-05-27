package org.nachc.tools.fhirtoomop.tools.populate.impl;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownloadDefaultFhirPatientFiles {

	public static File exec() {
		String url = AppParams.get("syntheaDefaultTestFhirPatientsUrl");
		String dir = AppParams.get("syntheaDefaultTestFhirPatientsDir");
		String zipFileName = "synthea_micro.zip";
		String msg = "Download default test patients...\n";
		msg += "---------------\n";
		msg += "Downloading synthea_micro as default test files:\n";
		msg += "dir = " + dir + "\n";
		msg += "zip = " + zipFileName + "\n";
		msg += "url = " + url + "\n";
		msg += "---------------\n";
		log.info(msg);
		HttpRequestClient client = new HttpRequestClient(url);
		log.info("Downloading file...");
		client.doGet();
		log.info("Writing to disc...");
		File dirFile = new File(dir);
		File zipFile = new File(dir, zipFileName);
		FileUtil.rmdir(dirFile);
		FileUtil.mkdirs(dirFile);
		FileUtil.createNewFile(zipFile);
		FileUtil.write(client.getResponseInputStream(), zipFile);
		log.info("Unzipping file");
		File unzipped = ZipUtil.unzip(zipFile, dirFile);
		unzipped = unzipped.getParentFile();
		log.info("File unzipped to: \n" + FileUtil.getCanonicalPath(unzipped));
		return unzipped;
	}
	
}
