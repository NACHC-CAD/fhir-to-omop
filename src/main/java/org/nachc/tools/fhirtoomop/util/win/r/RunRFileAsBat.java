package org.nachc.tools.fhirtoomop.util.win.r;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.nachc.tools.fhirtoomop.util.win.bat.RunBatFile;
import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunRFileAsBat {

	private static String DIR = "/temp/ponos";
	
	public static void run(String rScript) {
		writeFile(rScript);
		File bat = writeBatFile();
		runBatFile(bat);
//		deleteFiles();
	}
	
	private static void writeFile(String rScript) {
		log.info("Writing source file to /temp/ponos");
		File dir = new File(DIR);
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
		RunBatFile.exec(file);
	}

	private static void deleteFiles() {
		// DON'T DELETE THE FILES, THEY ARE USEFUL FOR DEBUGGNG ETC.
		File dir = new File(DIR);
		FileUtil.rmdir(dir);
	}
	
}
