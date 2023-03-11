package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ETLSYN02_LoadTestData {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		try {
			// get the file to run
			File file = FileUtil.getFile("/postgres/build/r/synthea-etl.R");
			String path = FileUtil.getCanonicalPath(file);
			String cmd = "Rscript " + path;
			log.info("Got file: " + path);
			log.info("Running cmd: " + cmd);
			// run r through runtime
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			// echo output
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				log.info(s);
			}
			// echo errors
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				log.info(s);
			}			
			log.info("Done.");			
		} catch(Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
	
}
