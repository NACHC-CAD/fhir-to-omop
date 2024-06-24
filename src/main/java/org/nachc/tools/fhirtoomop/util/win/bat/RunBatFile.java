package org.nachc.tools.fhirtoomop.util.win.bat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunBatFile {

	public static void exec(File file) {
		Timer timer = new Timer();
		timer.start();
		String fileName = FileUtil.getCanonicalPath(file);
		log.info("File: " + fileName);
		try {
			log.info("RUNNING PROCESS...");
			// start the process
			ProcessBuilder processBuilder = new ProcessBuilder(fileName);
			// redirect error to ouput to stdio
			processBuilder.redirectErrorStream(true);
			final Process process = processBuilder.start();
			// create the reader
			InputStream is = process.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isReader);
			String line;
			// read the output
			log.info("Script is running...\n\n");
			System.out.println("--- OUTPUT FROM SCRIPT -----------------------------------------\n\n");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			// wait for the process to finish
			int exitCode = process.waitFor();
			System.out.println("\n");
			System.out.println("--- END OUTPUT FROM SCRIPT -------------------------------------\n\n");
			// done
			log.info("EXIT CODE: " + exitCode);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		timer.stop();
		log.info("TIME TO RUN PROCESS: " + timer.getElapsedString());
		log.info("Done running process.");
	}

}
