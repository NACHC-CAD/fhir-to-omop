package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ACH1_RunAchilles {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		Timer timer = new Timer();
		timer.start();
		//		runRScript(FileUtil.getFile("/postgres/build/r/achilles/install-achilles.r"));
		File file = FileUtil.getFile("/postgres/build/r/achilles/run-achilles.bat");
		String fileName = FileUtil.getCanonicalPath(file);
		log.info("File: " + fileName);
		try {
			log.info("RUNNING PROCESS...");
			ProcessBuilder lmBuilder = new ProcessBuilder(fileName);

			lmBuilder.redirectErrorStream(true);
			final Process lmProcess = lmBuilder.start();
			InputStream is = lmProcess.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				log.info(line);
			}
			int result = lmProcess.waitFor(); //result becomes 0
			log.info("Result: " + result);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info("TIME TO RUN ACHILLES: " + timer.getElapsedString());
		log.info("Done running Ahcilles.");
	}

	private static void runRScript(File file) {
		try {
			log.info("\n\n\n-----------------------------------");
			log.info("RUNNING ACHILLES USING R-SCRIPTS");
			// get the file to run
			String path = FileUtil.getCanonicalPath(file);
			String cmd = "Rscript " + path;
			log.info("Got file: " + path);
			log.info("Running cmd: " + cmd);
			// run r through runtime
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			// echo output
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s;
			s = null;
			while ((s = stdInput.readLine()) != null) {
				log.info(s);
			}
			log.info("Done with Achilles, looking to see if there are any errors...");
			// echo errors
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			System.out.println("Here is the standard error of the command (if any):\n");
			s = null;
			boolean hasErrors = false;
			while ((s = stdError.readLine()) != null) {
				if (hasErrors == false) {
					hasErrors = true;
					log.error("ERRORS LOGGED DURING EXECUTION");
				}
				log.info(s);
			}
			log.info("Done processing R script");
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

}
