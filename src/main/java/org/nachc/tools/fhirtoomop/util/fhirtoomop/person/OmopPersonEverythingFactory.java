package org.nachc.tools.fhirtoomop.util.fhirtoomop.person;

import java.sql.Connection;
import java.util.List;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OmopPersonEverythingFactory {

	public static OmopPersonEverything makePerson(List<String> files, Connection conn) {
		// create the person based on the first file
		String firstPageFileName = getFirstPageFileName(files);
		String firstPageJson = FileUtil.getAsString(firstPageFileName);
		OmopPersonEverything rtn = new OmopPersonEverything(firstPageJson, conn);
		int cnt = 0;
		// add all the pages
		OmopPersonEverythingUpdater.addPages(rtn, files, conn);
		return rtn;
	}

	private static String getFirstPageFileName(List<String> fileNameList) {
		for (String filePath : fileNameList) {
			String fileName = getFileName(filePath);
			if (fileName.startsWith("0_")) {
				return filePath;
			}
		}
		return null;
	}

	private static String getFileName(String path) {
		int start = path.lastIndexOf('\\');
		if (start < 0) {
			start = path.lastIndexOf('/');
		}
		if (start < 0) {
			start = 0;
		}
		String rtn = path.substring(start + 1, path.length());
		return rtn;
	}

}
