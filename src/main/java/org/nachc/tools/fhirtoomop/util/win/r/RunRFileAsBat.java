package org.nachc.tools.fhirtoomop.util.win.r;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunRFileAsBat {

	public static void run(String rScript) {
		writeFile(rScript);
		File bat = writeBatFile();
		runBatFile(bat);
	}
	
	private static void writeFile(String rScript) {
		log.info("Writing source file to /temp/ponos");
		File dir = new File("/temp/ponos");
		FileUtil.rmdir(dir);
		FileUtil.mkdirs(dir);
		File file = new File(dir,"ponos.r");
		FileUtil.write(rScript, file);
		log.info("Done writing file: " + FileUtil.getCanonicalPath(file));
	}
	
	private static File writeBatFile() {
		log.info("Writing bat...");
		File file = new File("/temp/ponos/ponos.bat");
		FileUtil.write("rscript /temp/ponos/ponos.r", file);
		log.info("Done writing bat.");
		return file;
	}
	
	private static void runBatFile(File file) {
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
			while ((line = reader.readLine()) != null) {
				log.info(line);
			}
			// wait for the process to finish
			int exitCode = process.waitFor();
			// done
			log.info("EXIT CODE: " + exitCode);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		log.info("TIME TO RUN PROCESS: " + timer.getElapsedString());
		log.info("Done running process.");
	}

}
