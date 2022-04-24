package org.nachc.tools.fhirtoomop.tools.populate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.fhirtoomop.util.params.AppParams;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateOmopInstanceFromFhirFilesManualTest {

	@Test
	public void shouldUploadFiles() {
		log.info("Starting test...");
		File dir = new File(AppParams.getFhirPatientsDirName());
		File[] files = dir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		int cnt = 0;
		try {
			for(File file : files) {
				cnt++;
				fileNames.add(file.getCanonicalPath());
				if(cnt % 10000 == 0) {
					log.info(cnt + "");
				}
				if(cnt % 100000 == 0) {
					break;
				}
			}
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info(fileNames.get(0));
		log.info("got " + fileNames.size() + " files.");
		new PopulateOmopInstanceFromFhirFiles().exec(fileNames);
		log.info("Done.");
	}

}
