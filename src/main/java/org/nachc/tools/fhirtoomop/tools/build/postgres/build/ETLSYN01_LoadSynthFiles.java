package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.File;

import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ETLSYN01_LoadSynthFiles {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		try {
			log.info("\n\n\n-----------------------------------");
			log.info("LOADING TEST DATA USING ETL-Synthea R-SCRIPTS");
			// get the file to run
			String rScript = FileUtil.getAsString("/postgres/build/r/load-synthea-files.R");
			RunRFileAsBat.run(rScript);
			log.info("DONE LOADING TEST DATA USING ETL-Synthea R-SCRIPTS");
		} catch(Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
	
}
