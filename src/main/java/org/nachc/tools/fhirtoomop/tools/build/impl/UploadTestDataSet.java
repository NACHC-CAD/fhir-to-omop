package org.nachc.tools.fhirtoomop.tools.build.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadTestDataSet {

	private static final String FILE_NAME = "/broadsea/exported-test-data/demo_cdm.zip";

	public static void exec(Connection conn, File outputDir) {
		try {
			log.info("Unzipping file...");
			InputStream is = FileUtil.getInputStream(FILE_NAME);
			File target = ZipUtil.unzip(is, outputDir);
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(target));
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
