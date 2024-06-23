package org.nachc.tools.fhirtoomop.tools.build.achilles;

import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallAchilles {

	private static final String FILE = "/achilles/install-achilles.r";
	
	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		// run the install script
		log.info("---------------------");
		log.info("START: Installing Achilles...");
		RunRFileAsBat.run(FileUtil.getAsString(FILE));
		log.info("DONE: Installing Achilles...");
		log.info("---------------------");
		log.info("Done with Achilles install.");
	}

}












