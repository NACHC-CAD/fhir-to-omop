package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ETLSYN03_EtlSyntheaToCdm {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		try {
			log.info("\n\n\n-----------------------------------");
			log.info("LOADING TEST DATA USING ETL-Synthea R-SCRIPTS");
			// get the file to run
			File file = FileUtil.getFile("/postgres/build/r/write-synthea-to-cdm.R");
			RunRFileAsBat.run(FileUtil.getAsString("/postgres/build/r/write-synthea-to-cdm.R"));
			log.info("DONE LOADING TEST DATA USING ETL-Synthea R-SCRIPTS");
		} catch(Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
	
}
